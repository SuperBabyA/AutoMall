package com.itcase.automall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User extends AbsSuperObject implements Serializable {
    //账号唯一标识
    private Long id;
    //账号昵称
    private String accountName;
    //账号
    private String email;
    //密码
    private String password;
    //账号头像
    private String headPortrait;
    //用户名称
    private String userName;
    //用户性别
    private String sex;
    //联系方式
    private String telephone;
    //出生日期
    private String birthday;
    //身份证号
    private Long idCard;
    //是否为管理员
    private Integer isAdmin;
    //身份效验码
    private String token;
}
