package com.itcase.automall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Detail extends AbsSuperObject implements Serializable {
    //汽车详情唯一标识
    private Long id;
    //汽车外观色
    private String color;
    //汽车外观图
    private String detailImg;
    //挡的类型
    private String variableSpeed;
    //汽车座位数
    private String payload;
    //汽车轴距
    private Integer axleBase;
    //汽车扭矩
    private Integer torque;
    //汽车重量
    private Float weight;
    //发动机位置
    private String engine;
    //动力系统
    private String dynamicalSystem;
    //汽车排量
    private Float displacement;
    //汽车气缸数
    private Integer cylinderNumber;
    //驱动方式
    private String driveMode;
    //汽车描述
    private String descriptive;
    //汽车详情与汽车id关联的外键
    private Long carDetailId;

    //外键关联的实体
    private Car carDetail;

    @Override
    public String toString() {
        if (carDetail == null)
            return "Detail{" +
                "id='" + id + '\'' +
                ",color='" + color + '\'' +
                ", variableSpeed='" + variableSpeed + '\'' +
                ", payload='" + payload + '\'' +
                ", axleBase=" + axleBase +
                ", torque=" + torque +
                ", weight=" + weight +
                ", engine='" + engine + '\'' +
                ", dynamicalSystem='" + dynamicalSystem + '\'' +
                ", displacement=" + displacement +
                ", cylinderNumber=" + cylinderNumber +
                ", driveMode='" + driveMode + '\'' +
                ", descriptive='" + descriptive + '\'' +
                ", carDetailId=" + carDetailId +
                '}';
        else
            return "Detail{" +
                    "id='" + id + '\'' +
                    ",color='" + color + '\'' +
                    ", variableSpeed='" + variableSpeed + '\'' +
                    ", payload='" + payload + '\'' +
                    ", axleBase=" + axleBase +
                    ", torque=" + torque +
                    ", weight=" + weight +
                    ", engine='" + engine + '\'' +
                    ", dynamicalSystem='" + dynamicalSystem + '\'' +
                    ", displacement=" + displacement +
                    ", cylinderNumber=" + cylinderNumber +
                    ", driveMode='" + driveMode + '\'' +
                    ", descriptive='" + descriptive + '\'' +
                    ", carDetailId=" + carDetailId +
                    ", car=" + carDetail +
                    '}';
    }
}
