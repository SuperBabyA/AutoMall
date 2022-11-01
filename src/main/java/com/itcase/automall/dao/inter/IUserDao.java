package com.itcase.automall.dao.inter;

import com.itcase.automall.entity.AbsSuperObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface IUserDao extends IDoData{

    //用户登录使用
    AbsSuperObject findByEmail(@Param("cons") Map<String, Object> cons);
}
