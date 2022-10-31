package com.itcase.automall.dao.inter;

import com.itcase.automall.entity.AbsSuperObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IAddressDao extends IDoData{

    //根据userAddressId查询单个用户的所有地址
    List<AbsSuperObject> findUserAddress(Long id);
}
