package com.itcase.automall.bll.inter;

import com.itcase.automall.utils.HttpResult;

public interface IAddressService {

    //根据user_address_id字段查询单个用户的所有地址
    HttpResult findUserAddress();

    //根据user_address_id和订单id修改用户地址
    //HttpResult modifyUserAddress();
}
