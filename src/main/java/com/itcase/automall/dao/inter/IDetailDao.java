package com.itcase.automall.dao.inter;

import com.itcase.automall.entity.AbsSuperObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IDetailDao extends IDoData{

    //根据id查询汽车全部信息【联合查询】
    List<AbsSuperObject> findByIdCarAllInfo(Long id);
}
