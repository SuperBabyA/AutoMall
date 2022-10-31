package com.itcase.automall.test;

import com.itcase.automall.AutomallApplication;
import com.itcase.automall.bll.Impl.OrdersServiceImpl;
import com.itcase.automall.dao.inter.IOrdersDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Orders;
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
public class OrdersTest {

    @Autowired
    private Orders orders;

    @Autowired
    private IOrdersDao dao;

    /*------------------- 数据访问层测试 ------------------------*/

    @Test
    public void testOrdersFindAll() {
        log.info(dao+"");
        List<AbsSuperObject> orders = dao.findAllObj();
        orders.forEach(System.out::println);
    }


    @Test
    public void testOrdersFindId() {
        AbsSuperObject order = dao.findById(211112l);
        System.out.println(order);
    }

    @Test
    public void testOrdersAdd() {
        orders.setId(7575209520950l);
        orders.setOrderNumber(32498279058l);
        orders.setTradingHour(new Date());
        orders.setMoney(132.06);
        orders.setState("进行中");
        orders.setAddressOrderId(211112l);
        orders.setDetailOrderId(999202l);
        int result = dao.addObj(orders);
        System.out.println(result);
    }

    @Test
    public void testOrdersDel() {
        int result = dao.deleteObj(7575209520950l);
        System.out.println(result);
    }

    @Test
    public void testOrdersBatchDel() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(555503l);
        ids.add(555504l);
        int result = dao.batchDeleteByIds(ids);
        System.out.println(result);
    }

    @Test
    public void testOrdersUpdateObj() {
        orders.setId(555505l);
        orders.setOrderNumber(324982l);
        orders.setTradingHour(new Date());
        orders.setMoney(132.06);
        orders.setState("修改测试");
        orders.setAddressOrderId(100012l);
        orders.setDetailOrderId(345709423l);
        int result = dao.updateObj(orders);
        System.out.println(result);
    }

    /*@Test
    public void testFindUserOrder() {
        Map<String, Object> cons = new HashMap<>();
        cons.put("userOrderId",323457109l);
        //cons.put("state","进行中");
        List<AbsSuperObject> userOrders = dao.findUserOrder(cons);
        userOrders.forEach(System.out::println);
    }*/

    @Test
    public void testFindByPage() {
        HashMap<String, Object> cons = new HashMap<>();
        //cons.put("state","进行中");
        List<Map<String, Object>> byPage = dao.findByPage(cons, 0, 3);
        System.out.println(byPage);
    }

    /*--------------------业务逻辑层测试-------------------------*/

    @Autowired
    private OrdersServiceImpl bll;

    @Test
    public void testOrdersAdd2() {
        orders.setId(7575209520950l);
        orders.setOrderNumber(32498279058l);
        orders.setTradingHour(new Date());
        orders.setMoney(132.06);
        orders.setState("进行中");
        orders.setAddressOrderId(211112l);
        orders.setDetailOrderId(999202l);
        bll.setModel(orders);
        HttpResult httpResult = bll.save();
        System.out.println(httpResult);
    }


    @Test
    public void testUUID() {
        HashMap<String, Object> cons = new HashMap<>();
        HttpResult httpResult = bll.findByPage(cons, 1, 5);
        System.out.println(httpResult);
    }
}
