package com.itcase.automall.dao.inter;

import com.itcase.automall.entity.AbsSuperObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ICategoryDao extends IDoData{

    //多条件查询【车型、价格、日期】有问题！！！
    List<AbsSuperObject> multipleCriteriaQuery(@Param("cons") Map<String,Object> cons);
}
