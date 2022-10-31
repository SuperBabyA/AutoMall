package com.itcase.automall.test;

import com.itcase.automall.AutomallApplication;
import com.itcase.automall.bll.Impl.CollectionServiceImpl;
import com.itcase.automall.dao.inter.ICollectionDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Collection;
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
public class CollectionTest {

    @Autowired
    private Collection collection;

    @Autowired
    private ICollectionDao dao;

    /*------------------- 数据访问层测试 ------------------------*/

    @Test
    public void testCollectionFindAll() {
        log.info(dao+"");
        List<AbsSuperObject> collections = dao.findAllObj();
        collections.forEach(System.out::println);
    }

    @Test
    public void testCollectionFindId() {
        AbsSuperObject collection = dao.findById(444401l);
        System.out.println(collection);
    }

    @Test
    public void testCollectionAdd() {
        collection.setId(444407l);
        collection.setUserCollectionId(345709423l);
        collection.setCarCollectionId(888887l);
        int result = dao.addObj(collection);
        System.out.println(result);
    }

    @Test
    public void testCollectionDel() {
        int result = dao.deleteObj(444407l);
        System.out.println(result);
    }

    @Test
    public void testCollectionBatchDel() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(444406l);
        ids.add(444405l);
        //ids.add(13241324l);
        int result = dao.batchDeleteByIds(ids);
        System.out.println(result);
    }

    @Test
    public void testFindUserCollection() {
        List<AbsSuperObject> colls = dao.findUserCollection(345709423l);
        colls.forEach(System.out::println);
    }

    @Test
    public void testCollectionUpdateObj() {
        collection.setId(444403l);
        collection.setUserCollectionId(345709423l);
        collection.setCarCollectionId(666664l);
        int result = dao.updateObj(collection);
        System.out.println(result);
    }

    /*----------------------业务逻辑层测试---------------------------*/

    @Autowired
    private CollectionServiceImpl bll;

    @Test
    public void testFindUserCollection2() {
        collection.setUserCollectionId(345709423l);
        bll.setModel(collection);
        HttpResult httpResult = bll.findUserCollection();
        System.out.println(httpResult.getObject());
    }
}
