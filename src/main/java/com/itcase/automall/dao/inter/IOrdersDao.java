package com.itcase.automall.dao.inter;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IOrdersDao extends IDoData{

    //根据所给条件查询用户的所有订单   条件【交易用户id、状态state】
    //List<AbsSuperObject> findUserOrder(@Param("cons") Map<String,Object> cons);
}
