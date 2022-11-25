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
public class Reservation extends AbsSuperObject implements Serializable {
    //唯一标识
    private Long id;

    //预约者姓名
    private String subscribers;

    //预约者邮箱
    private String email;

    //预约者电话
    private String telephone;

    //预约车型
    private String reservationModel;

    //预约时间
    private String reservationTime;

    //是否处理
    private String dispose;
}
