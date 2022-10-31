package com.itcase.automall.bll.inter;

import com.itcase.automall.utils.HttpResult;

public interface IDetailService {

    //根据id查询汽车全部信息【联合查询】
    HttpResult findByIdCarAllInfo();
}
