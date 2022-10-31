package com.itcase.automall.test;

import com.itcase.automall.AutomallApplication;
import com.itcase.automall.bll.Impl.CategoryServiceImpl;
import com.itcase.automall.dao.inter.ICategoryDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Category;
import com.itcase.automall.utils.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutomallApplication.class)
public class CategoryTest {

    @Autowired
    private Category category;

    @Autowired
    private ICategoryDao dao;

    /*------------------- 数据访问层测试 ------------------------*/

    @Test
    public void testCategoryFindAll() {
        log.info(dao+"");
        List<AbsSuperObject> category = dao.findAllObj();
        category.forEach(System.out::println);
    }

    @Test
    public void testCategoryFindId() {
        AbsSuperObject category = dao.findById(100001l);
        System.out.println(category);
    }

    @Test
    public void testCategoryAdd() {
        category.setId(100015l);
        category.setGenre("测试添加4");
        category.setSeries("测试添加4");
        category.setBrand("测试添加4");
        int result = dao.addObj(category);
        System.out.println(result);
    }

    @Test
    public void testCategoryDel() {
        int result = dao.deleteObj(100015l);
        System.out.println(result);
    }

    @Test
    public void testCategoryBatchDel() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(100015l);
        ids.add(100014l);
        int result = dao.batchDeleteByIds(ids);
        System.out.println(result);
    }

    @Test
    public void testCategoryUpdateObj() {
        category.setId(100012l);
        category.setGenre("测试修改1");
        category.setSeries("测试修改2");
        category.setBrand("测试修改3");
        int result = dao.updateObj(category);
        System.out.println(result);
    }

    //测试多条件查询
    @Test
    public void testMultipleCriteriaQuery() throws ParseException {
        Map<String, Object> cons = new HashMap<>();
        cons.put("minPrice",20.36f);
        cons.put("maxPrice",220.36f);
        cons.put("shelvesTime",(new SimpleDateFormat("yyyy-MM-dd")).parse("2018-01-01"));
        cons.put("genre","SUV");
        List<AbsSuperObject> cars = dao.multipleCriteriaQuery(cons);
        cars.forEach(System.out::println);
    }

    @Test
    public void testFindByPage() {
        Map<String, Object> cons = new HashMap<>();
        List<Map<String, Object>> courses = dao.findByPage(cons, 0, 5);
        courses.forEach(System.out::println);
    }

    /*---------------------业务逻辑层测试------------------------*/

    @Autowired
    private CategoryServiceImpl bll;

    @Test
    public void testFindByPage2() {
        Map<String, Object> cons = new HashMap<>();
//        cons.put("genre","SUV");
        HttpResult httpResult = bll.findByPage(cons, 1, 3);
        System.out.println(httpResult.getObject());
        System.out.println(httpResult.getMessage());
    }

}
