package com.itcase.automall.test;

import com.itcase.automall.AutomallApplication;
import com.itcase.automall.bll.Impl.CarServiceImpl;
import com.itcase.automall.dao.inter.ICarDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Car;
import com.itcase.automall.utils.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@Slf4j
@SpringBootTest(classes = AutomallApplication.class)
@RunWith(SpringRunner.class)
public class CarTest {

    @Autowired
    private Car car;

    @Autowired
    private ICarDao dao;

    @Test
    public void testCarAdd() {
        car.setId(111115l);
        car.setCarName("测试5");
        car.setPrice(54.06f);
        car.setSelling(0);
        car.setShelvesTime(new Date());
        int result = dao.addObj(car);
        System.out.println(result);
    }

    @Test
    public void testCarFindAll() {
        List<AbsSuperObject> cars = dao.findAllObj();
        cars.forEach(System.out::println);
    }

    @Test
    public void testCarFindId() {
        AbsSuperObject car = dao.findById(888882l);
        log.info("car{}",car);
    }

    @Test
    public void testCarDel() {
        int result = dao.deleteObj(111115l);
        System.out.println(result);
    }

    @Test
    public void testCarBatchDel() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(111114l);
        ids.add(111112l);
        ids.add(111113l);
        int result = dao.batchDeleteByIds(ids);
        System.out.println(result);
    }

    @Test
    public void testCarUpdateObj() {
        car.setId(111111l);
        car.setCarName("测试修改111");
        car.setPrice(54.06f);
        car.setSelling(0);
        car.setShelvesTime(new Date());
        int result = dao.updateObj(car);
        System.out.println(result);
    }

    @Test
    public void testFindByPage() {
        Map<String, Object> cons = new HashMap<>();
        List<Map<String, Object>> result = dao.findByPage(cons, 0, 5);
        System.out.println(result);
    }

    @Autowired
    private CarServiceImpl bll;

    @Test
    public void testFindByPage2() {
        Map<String, Object> cons = new HashMap<>();
        HttpResult httpResult = bll.findByPage(cons, 1, 5);
        System.out.println(httpResult);
    }
}
