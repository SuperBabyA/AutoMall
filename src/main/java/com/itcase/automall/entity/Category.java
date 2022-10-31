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
public class Category extends AbsSuperObject implements Serializable {
    //汽车类型唯一表示
    private Long id;
    //汽车车型
    private String genre;
    //汽车系别
    private String series;
    //汽车品牌
    private String brand;
}
