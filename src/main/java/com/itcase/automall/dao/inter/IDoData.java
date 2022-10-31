package com.itcase.automall.dao.inter;

import com.itcase.automall.entity.AbsSuperObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface IDoData {

    //新增对象
    int addObj(AbsSuperObject object);

    //根据唯一标识删除对象
    int deleteObj(Long id);

    //根据所给集合进行批量删除
    int batchDeleteByIds(List<Long> ids);

    //查询所有对象
    List<AbsSuperObject> findAllObj();

    //根据唯一标识查询单个对象
    AbsSuperObject findById(Long id);

    //根据唯一标识修改单个对象
    int updateObj(AbsSuperObject object);

    //分页查询
    List<Map<String,Object>> findByPage(@Param("cons") Map<String,Object> cons,     //查询条件
                                        @Param("start") int start,              //起始查询记录数
                                        @Param("rowsCount") int rowsCount);     //每页查询记录数
    //显示查询总条数，参数与分页查找绑定
    int getRowsCount(@Param("cons") Map<String,Object> cons);

}
