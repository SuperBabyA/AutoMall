package com.itcase.automall.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.controller.inter.IFeedbackController;
import com.itcase.automall.entity.Feedback;
import com.itcase.automall.utils.HttpResult;
import com.itcase.automall.utils.snowflake.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/feedback")
public class FeedbackControllerImpl extends AbsSuperController implements IFeedbackController {

    @Autowired
    private Feedback feedback;

    @Autowired
    @Qualifier("feedbackServiceImpl")
    private AbsSuperService bll;

    @Override
    public AbsSuperService getBll() {
        return bll;
    }

    //根据id删除反馈对象
    @DeleteMapping("/delete_feedback/{id}")
    public HttpResult delFeedback(@PathVariable("id") Long id) throws IOException {
        feedback.setId(id);
        getBll().setModel(feedback);
        HttpResult httpResult = getBll().delete();
        return httpResult;
    }

    //多条件分页查询反馈对象
    @GetMapping("find_feedback/{pageNumber}/{rowsCount}/{dispose}")
    public HttpResult findByPage(@PathVariable("pageNumber") Integer pageNumber,
                             @PathVariable("rowsCount") Integer rowsCount,
                             @PathVariable("dispose") String dispose) throws IOException {
        HashMap<String, Object> cons = new HashMap<>();
        if (!"-1".equals(dispose) && dispose != null &&
                !"".equals(dispose))
            cons.put("dispose",dispose);
        HttpResult httpResult = getBll().findByPage(cons, pageNumber, rowsCount);
        return httpResult;
    }

    //新增反馈对象
    @PostMapping("/save_feedback")
    public HttpResult saveFeedback(@RequestBody String fbString) throws IOException {
        Feedback fb = new ObjectMapper().readValue(fbString, Feedback.class);
        fb.setId(new IdGeneratorSnowflake().snowflakeId());
        System.out.println(fb);
        getBll().setModel(fb);
        HttpResult httpResult = getBll().save();
        return httpResult;
    }

    //根据id修改反馈实例对象
    @PutMapping("/edit_feedback")
    public HttpResult editFeedback(@RequestBody String fbString) throws IOException {
        Feedback fb = new ObjectMapper().readValue(fbString, Feedback.class);
        System.out.println(fb);
        getBll().setModel(fb);
        HttpResult httpResult = getBll().update();
        return httpResult;
    }

    //根据id批量删除反馈
    @PostMapping("/batch_del_feedback")
    public HttpResult batchDelFeedback(@RequestBody List<Long> ids) throws IOException {
        HttpResult httpResult = getBll().batchDel(ids);
        return httpResult;
    }
}
