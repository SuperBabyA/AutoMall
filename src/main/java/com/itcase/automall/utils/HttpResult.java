package com.itcase.automall.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResult {
    //状态码
    private String code;
    //提示信息
    private String message;
    //返回对象
    private Object data;
}
