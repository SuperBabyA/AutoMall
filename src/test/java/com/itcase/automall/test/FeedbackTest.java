package com.itcase.automall.test;

import com.itcase.automall.AutomallApplication;
import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.bll.Impl.FeedbackServiceImpl;
import com.itcase.automall.dao.inter.IFeedbackDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Feedback;
import com.itcase.automall.utils.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutomallApplication.class)
public class FeedbackTest {

    @Autowired
    private Feedback feedback;

    @Autowired
    private IFeedbackDao dao;


    /*------------------- 数据访问层测试 ------------------------*/

    @Test
    public void testFeedbackFindAll() {
        log.info(dao+"");
        List<AbsSuperObject> feedback = dao.findAllObj();
        feedback.forEach(System.out::println);
    }

    @Test
    public void testFeedbackFindId() {
        AbsSuperObject feedback = dao.findById(777701l);
        System.out.println(feedback);
    }

    @Test
    public void testFeedbackAdd() {
        feedback.setId(777705l);
        feedback.setPhone("15832498881");
        feedback.setEmail("333@qq.com");
        feedback.setContent("测试2");
        feedback.setMotorcycleType("测试2");
        int result = dao.addObj(feedback);
        System.out.println(result);
    }

    @Test
    public void testFeedbackDel() {
        int result = dao.deleteObj(777705l);
        System.out.println(result);
    }

    @Test
    public void testFeedbackBatchDel() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(777704l);
        ids.add(777705l);
        int result = dao.batchDeleteByIds(ids);
        System.out.println(result);
    }

    @Test
    public void testFeedbackUpdateObj() {
        feedback.setId(777703l);
        feedback.setPhone("15832498881");
        feedback.setEmail("333@qq.com");
        feedback.setContent("测试修改");
        feedback.setMotorcycleType("福特野马");
        int result = dao.updateObj(feedback);
        System.out.println(result);
    }

    @Test
    public void testFindByPage() {
        HashMap<String, Object> cons = new HashMap<>();
        List<Map<String, Object>> byPage = dao.findByPage(cons, 3, 3);
        System.out.println(byPage);
    }

    /*-------------------业务逻辑层测试-----------------------*/

    @Autowired
    private FeedbackServiceImpl bll;

    @Test
    public void testFindByPage2() {
        HashMap<String, Object> cons = new HashMap<>();
        HttpResult httpResult = bll.findByPage(cons, 1, 3);
        System.out.println(httpResult);
    }
}
