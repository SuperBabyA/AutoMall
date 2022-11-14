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
public class Car extends AbsSuperObject implements Serializable {
    //汽车唯一标识
    private Long id;
    //汽车名称
    private String carName;
    //汽车价格
    private Float price;
    //汽车外观图
    private String thumbImg;
    //是否在销 0:在售 1:下架
    private Integer selling;
    //上架时间
    private String shelvesTime;

    //汽车类别外键
    private Long categoryCarId;

    private Category categoryCar;
}
