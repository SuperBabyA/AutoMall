package com.itcase.automall.dao.inter;

import com.itcase.automall.entity.AbsSuperObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ICollectionDao extends IDoData{

    //根据用户id查询用户的全部收藏
    List<AbsSuperObject> findUserCollection(Long userCollectionId);
}
