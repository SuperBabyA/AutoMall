package com.itcase.automall.controller.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.bll.Impl.AddressServiceImpl;
import com.itcase.automall.controller.inter.IAddressController;
import com.itcase.automall.controller.inter.IUserController;
import com.itcase.automall.entity.Address;
import com.itcase.automall.utils.HttpResult;
import com.itcase.automall.utils.snowflake.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/address")
public class AddressControllerImpl extends AbsSuperController implements IAddressController {

    @Autowired
    private Address address;

    @Autowired
    private AddressServiceImpl addressService;

    @Autowired
    @Qualifier("addressServiceImpl")
    private AbsSuperService bll;

    @Override
    public AbsSuperService getBll() {
        return bll;
    }

    //查询单个用户的全部地址
    @GetMapping("/user_addr/{id}")
    public String findUserAllAddr(@PathVariable("id") Long id) throws IOException {
        address.setUserAddressId(id);
        addressService.setModel(address);
        HttpResult httpResult = addressService.findUserAddress();
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //修改单个用户的地址
    @PutMapping("/modify_addr")
    public String modifyUserAddress(@RequestBody String addrString) throws IOException {
        System.out.println(addrString);
        Address addr = new ObjectMapper().readValue(addrString, Address.class);
        System.out.println(addr);
        getBll().setModel(addr);
        HttpResult httpResult = getBll().update();
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //新增用户地址
    @PostMapping("/save_addr")
    public String saveAddr(@RequestBody String addrString) throws IOException {
        Address addr = new ObjectMapper().readValue(addrString, Address.class);
        addr.setId(new IdGeneratorSnowflake().snowflakeId());
        getBll().setModel(addr);
        HttpResult httpResult = getBll().save();
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //根据地址id删除用户地址
    @DeleteMapping("/delete_addr/{id}")
    public String delAddr(@PathVariable("id")Long id) throws IOException {
        address.setId(id);
        getBll().setModel(address);
        HttpResult httpResult = getBll().delete();
        return new ObjectMapper().writeValueAsString(httpResult);
    }
}
