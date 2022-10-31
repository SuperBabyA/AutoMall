package com.itcase.automall.test;

import com.itcase.automall.AutomallApplication;
import com.itcase.automall.bll.Impl.AddressServiceImpl;
import com.itcase.automall.dao.inter.IAddressDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Address;
import com.itcase.automall.utils.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutomallApplication.class)
public class AddressTest {

    @Autowired
    private Address address;

    @Autowired
    private IAddressDao dao;

    /*------------------- 数据访问层测试 ------------------------*/

    @Test
    public void testAddressFindAll() {
        log.info(dao+"");
        List<AbsSuperObject> address = dao.findAllObj();
        address.forEach(System.out::println);
    }

    @Test
    public void testAddressFindId() {
        AbsSuperObject address = dao.findById(111111l);
        System.out.println(address);
    }

    @Test
    public void testAddressAdd() {
        address.setId(111116l);
        address.setProvince("测试3");
        address.setCity("测试3");
        address.setDistrict("测试3");
        address.setParticular("大东骏工公司203");
        address.setDefaultSetting(1);
        address.setUserAddressId(323457109l);
        int result = dao.addObj(address);
        System.out.println(result);
    }

    @Test
    public void testAddressDel() {
        int result = dao.deleteObj(111116l);
        System.out.println(result);
    }

    @Test
    public void testAddressBatchDel() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(111114l);
        ids.add(111115l);
        //ids.add(13241324l);
        int result = dao.batchDeleteByIds(ids);
        System.out.println(result);
    }

    @Test
    public void testAddressUpdateObj() {
        address.setId(111114l);
        address.setProvince("测试更新1");
        address.setCity("测试更新1");
        address.setDistrict("测试更新1");
        address.setParticular("测试更新1");
        address.setDefaultSetting(1);
        address.setUserAddressId(323457109l);
        System.out.println(address);
    }

    @Test
    public void testFindUserAddress() {
        List<AbsSuperObject> userAddress = dao.findUserAddress(323457109l);
        userAddress.forEach(System.out::println);
    }

    @Test
    public void testModifyUserAddress() {
        address.setId(311112l);
        address.setUserAddressId(345709423l);
        address.setProvince("山东");
        address.setCity("菏泽");
        address.setDistrict("曹县");
        address.setParticular("777村");
        address.setDefaultSetting(1);
        //int result = dao.modifyUserAddress(address);
        //System.out.println(result);
    }

    /*---------------------业务逻辑层测试--------------------------*/

    @Autowired
    private AddressServiceImpl bll;

    @Test
    public void testFindUserAddress2() {
        address.setUserAddressId(323457109l);
        bll.setModel(address);
        HttpResult httpResult = bll.findUserAddress();
        System.out.println(httpResult.getMessage());
        System.out.println(httpResult.getObject());
        System.out.println(httpResult.getCode());
    }

    @Test
    public void testModifyUserAddress2() {
        address.setId(311112l);
        address.setUserAddressId(345709423l);
        address.setProvince("山东");
        address.setCity("菏泽");
        address.setDistrict("曹县");
        address.setParticular("天河猎户村666");
        address.setDefaultSetting(1);
        bll.setModel(address);
//        HttpResult httpResult = bll.modifyUserAddress();
//        System.out.println(httpResult.getObject());
    }
}
