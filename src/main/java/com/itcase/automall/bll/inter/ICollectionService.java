package com.itcase.automall.bll.inter;

import com.itcase.automall.utils.HttpResult;

public interface ICollectionService {

    //查看单个用户的收藏列表
    HttpResult findUserCollection();
}
