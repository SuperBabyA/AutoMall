package com.itcase.automall.test;

import com.itcase.automall.AutomallApplication;
import com.itcase.automall.bll.Impl.UserServiceImpl;
import com.itcase.automall.dao.inter.IUserDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.User;
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
public class UserTest {

    @Autowired
    private User user;

    @Autowired
    private IUserDao dao;

    /*--------------------- 数据访问层测试 ------------------------*/

    @Test
    public void testUserFindAll() {
        log.info(dao+"");
        List<AbsSuperObject> users = dao.findAllObj();
        users.forEach(System.out::println);
    }

    @Test
    public void testUserFindId() {
        AbsSuperObject user = dao.findById(323457109L);
        System.out.println(user);
    }

    @Test
    public void testUserAdd() {
        user.setId(13241324L);
        user.setEmail("1234l");
        user.setAccountName("测试人员3");
        user.setBirthday(new Date());
        user.setIdCard(564352688394819832l);
        user.setPassword("43214321");
        user.setTelephone("18758391823");
        user.setIsAdmin(0);
        user.setSex("男");
        user.setUserName("测试3");
        int result = dao.addObj(user);
        System.out.println(result);
    }

    @Test
    public void testUserDel() {
        int result = dao.deleteObj(12341234L);
        System.out.println(result);
    }

    @Test
    public void testUserBatchDel() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(12341234l);
        ids.add(43214321l);
        //ids.add(13241324l);
        int result = dao.batchDeleteByIds(ids);
        System.out.println(result);
    }

    @Test
    public void testUserUpdateObj() {
        user.setId(13241324l);
        user.setEmail("23463452l");
        user.setAccountName("测试修改4");
        user.setBirthday(new Date());
        user.setIdCard(564352688394819832l);
        user.setPassword("43214321");
        user.setTelephone("18758391823");
        user.setIsAdmin(0);
        user.setSex("男");
        user.setUserName("测试修改");
        int result = dao.updateObj(user);
        System.out.println(result);
    }

    @Test
    public void testFindByPage() {
        Map<String, Object> cons = new HashMap<>();
        //cons.put("accountName","汽车");
        List<Map<String, Object>> courses = dao.findByPage(cons, 3, 3);
        courses.forEach(System.out::println);
    }

    @Test
    public void testetRowsCount() {
        Map<String, Object> cons = new HashMap<>();
        cons.put("accountName","汽车");
        int rowsCount = dao.getRowsCount(cons);
        System.out.println(rowsCount);
    }

    @Test
    public void testFindByAccount() {
        HashMap<String, Object> cons = new HashMap<>();
        cons.put("account",1111111111l);
        cons.put("password","111111");
        cons.put("isAdmin",1);
        AbsSuperObject user = dao.findByEmail(cons);
        System.out.println(user);
    }

    /*--------------------- 业务逻辑层测试 ------------------------*/

    @Autowired
    private UserServiceImpl bll;

    @Test
    public void testSaveObj() {
        user.setId(12341234l);
        user.setAccountName("业务逻辑层测试1");
        user.setEmail("2345345l");
        user.setPassword("12341234");
        user.setUserName("业务逻辑层测试1");
        user.setSex("男");
        user.setTelephone("15898982281");
        user.setBirthday(new Date());
        user.setIdCard(12864913641028374l);
        user.setIsAdmin(0);
        bll.setModel(user);
        HttpResult httpResult = bll.save();
        log.info("httpResult{}",httpResult);
    }

    @Test
    public void testDeleteObj() {
        user.setId(23452345l);//12341234l
        //bll.setModel(user);
        HttpResult httpResult = bll.delete();
        log.info("httpResult{}",httpResult);
    }

    @Test
    public void testFindAll() {
        HttpResult httpResult = bll.findAll();
        System.out.println(httpResult.getData());
    }

    @Test
    public void testFindById() {
        //user.setId(23452345l);//345709423l
        bll.setModel(user);
        HttpResult httpResult = bll.findById();
        System.out.println(httpResult.getData());
    }

    @Test
    public void testUpdate() {
        user.setId(12341234l);
        user.setAccountName("业务逻辑层update测试1");
        user.setEmail("234566l");
        user.setPassword("12341234");
        user.setUserName("业务逻辑层update测试1");
        user.setSex("男");
        user.setTelephone("15898982281");
        user.setBirthday(new Date());
        user.setIdCard(12864913641028374l);
        user.setIsAdmin(0);
        bll.setModel(user);
        HttpResult httpResult = bll.update();
        log.info("httpResult{}",httpResult);
    }

    @Test
    public void testBatchDel() {
        ArrayList<Long> cons = new ArrayList<>();
        //cons.addAll(Arrays.asList(12341234l,23452345l,13241324l));
        HttpResult httpResult = bll.batchDel(cons);
        System.out.println(httpResult.getCode());
        System.out.println(httpResult.getMessage());
        System.out.println(httpResult.getData());
    }

    @Test
    public void testetRowsCount2() {
        Map<String, Object> cons = new HashMap<>();
        cons.put("accountName","汽车");
        List<Map<String, Object>> courses = dao.findByPage(cons, 3, 3);
        courses.forEach(System.out::println);
    }

    @Test
    public void testFindByAccount2() {
        HashMap<String, Object> cons = new HashMap<>();
        cons.put("account",1111111111l);
        cons.put("password","111111");
        cons.put("isAdmin",0);
        HttpResult httpResult = bll.findByEmail(cons);
        System.out.println(httpResult.getMessage());
        System.out.println(httpResult.getData());
    }

    @Test
    public void testFindByPage2() {
        Map<String, Object> cons = new HashMap<>();
        HttpResult httpResult = bll.findByPage(cons, 1, 4);
        System.out.println(httpResult);
    }

}
