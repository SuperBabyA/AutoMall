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
public class Orders extends AbsSuperObject implements Serializable {
    //订单唯一标识
    private Long id;
    //订单编号
    private String orderNumber;
    //交易时间
    private String tradingHour;
    //交易金额
    private Double money;
    //交易状态
    private String state;
    //订单与汽车类型关联的外键
    private Long detailOrderId;
    //订单与地址关联的外键
    private Long addressOrderId;

    private Detail detailOrder;
    private Address addressOrder;
}
