package com.itcase.automall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Collection extends AbsSuperObject implements Serializable {
    //收藏的唯一标识
    private Long id;
    //收藏时间
    private Date collectionTime;
    //收藏关联用户唯一标识【外键】
    private Long userCollectionId;
    //收藏关联汽车类别唯一标识【外键】
    private Long carCollectionId;

    private User userCollection;
    private Car carCollection;

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ",collectionTime=" + collectionTime +
                ",userCollectionId=" + userCollectionId +
                ", carCollectionId=" + carCollectionId +
                ", userCollection=" + userCollection +
                ", carCollection=" + carCollection +
                '}';
    }
}
