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
public class Feedback extends AbsSuperObject implements Serializable {
    //反馈记录的唯一标识
    private Long id;
    //联系电话
    private String phone;
    //邮箱
    private String email;
    //反馈内容
    private String content;
    //反馈车型
    private String motorcycleType;
    //是否处理
    private String dispose;
}
