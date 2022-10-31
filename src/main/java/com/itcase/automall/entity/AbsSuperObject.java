package com.itcase.automall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsSuperObject implements Serializable {
    //机内码
    private Long id;
    //名称
    private String name;
    //描述
    private String description;
    //创建日期
    private Date createDate;
    //更新日期
    private Date updateDate;
    //业务发生时间
    private String businessDate;
    //状态
    private String status;
}
