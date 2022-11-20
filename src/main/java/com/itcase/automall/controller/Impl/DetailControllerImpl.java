package com.itcase.automall.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.bll.Impl.DetailServiceImpl;
import com.itcase.automall.controller.inter.IDetailController;
import com.itcase.automall.entity.Detail;
import com.itcase.automall.utils.HttpResult;
import com.itcase.automall.utils.snowflake.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/detail")
public class DetailControllerImpl extends AbsSuperController implements IDetailController {

    @Autowired
    private Detail detail;

    @Autowired
    @Qualifier("detailServiceImpl")
    private AbsSuperService Service;

    @Autowired
    private DetailServiceImpl detailService;

    @Override
    public AbsSuperService getBll() {
        return Service;
    }

    //根据详情id删除单个汽车详情信息
    @DeleteMapping("/delete_detail/{id}")
    public HttpResult deleteDetail(@PathVariable("id") Long id) throws IOException {
        detail.setId(id);
        getBll().setModel(detail);
        HttpResult httpResult = getBll().delete();
        return httpResult;
    }

    //根据详情外键car_detail_id查询汽车全部信息【进入汽车详情页】
    @GetMapping("/find_all_car_info/{id}")
    public HttpResult findAllCarInfo(@PathVariable("id") Long id) throws IOException {
        detail.setCarDetailId(id);
        detailService.setModel(detail);
        HttpResult httpResult = detailService.findByIdCarAllInfo();
        System.out.println(httpResult);
        return httpResult;
    }

    //新增汽车详情对象
    @PostMapping("/save_detail")
    public HttpResult saveDetail(@RequestBody String detailStr) throws IOException {
        Detail detailObj = new ObjectMapper().readValue(detailStr, Detail.class);
        detailObj.setId(new IdGeneratorSnowflake().snowflakeId());
        getBll().setModel(detailObj);
        HttpResult httpResult = getBll().save();
        return httpResult;
    }

    //根据id修改汽车详情对象
    @PutMapping("/edit_detail")
    public HttpResult editDetail(@RequestBody String detailStr) throws IOException {
        Detail detailObj = new ObjectMapper().readValue(detailStr, Detail.class);
        getBll().setModel(detailObj);
        HttpResult httpResult = getBll().update();
        return httpResult;
    }

}
