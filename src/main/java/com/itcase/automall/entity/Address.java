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
public class Address extends AbsSuperObject implements Serializable {
    //地址唯一标识
    private Long id;
    //所在省
    private String province;
    //所在市
    private String city;
    //所在区
    private String district;
    //地址详情
    private String particular;
    //是否设为默认地址 0：默认 ，1：不默认
    private Integer defaultSetting;
    //关联的用户唯一标识【外键】
    private Long userAddressId;

    private User userAddress;
}
