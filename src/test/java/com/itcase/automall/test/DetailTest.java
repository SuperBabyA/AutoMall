package com.itcase.automall.test;

import com.itcase.automall.AutomallApplication;
import com.itcase.automall.bll.Impl.DetailServiceImpl;
import com.itcase.automall.bll.inter.IDetailService;
import com.itcase.automall.dao.inter.IDetailDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Detail;
import com.itcase.automall.utils.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutomallApplication.class)
public class DetailTest {

    @Autowired
    private Detail detail;

    @Autowired
    private IDetailDao dao;

    /*------------------- 数据访问层测试 ------------------------*/

    @Test
    public void testDetailFindAll() {
        log.info(dao+"");
        List<AbsSuperObject> details = dao.findAllObj();
        details.forEach(System.out::println);
    }

    @Test
    public void testDetailFindId() {
        AbsSuperObject detail = dao.findById(111113l);
        System.out.println(detail);
    }

    @Test
    public void testDetailAdd() {
        detail.setId(111114l);
        detail.setColor("测试4");
        detail.setAxleBase(234);
        detail.setCylinderNumber(4);
        detail.setDescriptive("测试4");
        detail.setDisplacement(1.4f);
        detail.setDriveMode("测试4");
        detail.setDynamicalSystem("测试4");
        detail.setTorque(4);
        detail.setPayload("测试4");
        detail.setVariableSpeed("测试4");
        detail.setWeight(3200f);
        detail.setCarDetailId(666661l);
        detail.setEngine("测试4");
        int result = dao.addObj(detail);
        System.out.println(result);
    }

    @Test
    public void testDetailDel() {
        int result = dao.deleteObj(111114l);
        System.out.println(result);
    }

    @Test
    public void testDetailBatchDel() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(111112l);
        ids.add(111113l);
        //ids.add(13241324l);
        int result = dao.batchDeleteByIds(ids);
        System.out.println(result);
    }

    @Test
    public void testDetailUpdateObj() {
        detail.setId(111114l);
        detail.setColor("测试修改4");
        detail.setAxleBase(234);
        detail.setCylinderNumber(4);
        detail.setDescriptive("测试修改4");
        detail.setDisplacement(1.4f);
        detail.setDriveMode("测试修改4");
        detail.setDynamicalSystem("测试修改4");
        detail.setTorque(4);
        detail.setPayload("测试修改4");
        detail.setVariableSpeed("测试修改4");
        detail.setWeight(3200f);
        detail.setCarDetailId(666661l);
        detail.setEngine("测试修改4");
        int result = dao.updateObj(detail);
        System.out.println(result);
    }

    @Test
    public void testFindByIdCarAllInfo() {
        List<AbsSuperObject> carAllInfo = dao.findByIdCarAllInfo(666663l);
        log.info("@@@"+carAllInfo);
    }



    /*-----------------------业务逻辑层测试-----------------------------*/

    @Autowired
    private DetailServiceImpl bll;

    @Test
    public void testFindByIdCarAllInfo2() {
        detail.setCarDetailId(666661l);
        bll.setModel(detail);
        HttpResult httpResult = bll.findByIdCarAllInfo();
        System.out.println(httpResult.getMessage());
        System.out.println(httpResult.getObject());
        System.out.println(httpResult.getCode());
    }

    @Test
    public void testSave() {
        detail.setId(9753589543779l);
        detail.setColor("测试修改0");
        detail.setAxleBase(234);
        detail.setCylinderNumber(4);
        detail.setDescriptive("测试修改0");
        detail.setDisplacement(1.4f);
        detail.setDriveMode("测试修改0");
        detail.setDynamicalSystem("测试修改0");
        detail.setTorque(4);
        detail.setPayload("测试修改0");
        detail.setVariableSpeed("测试修改0");
        detail.setWeight(3200f);
        detail.setCarDetailId(888887l);
        detail.setEngine("测试修改0");
        bll.setModel(detail);
        HttpResult httpResult = bll.save();
        System.out.println(httpResult.getMessage());
        System.out.println(httpResult.getCode());
        System.out.println(httpResult.getObject());
    }


}
