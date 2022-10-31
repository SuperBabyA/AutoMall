package com.itcase.automall.controller.Impl;

import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.entity.AbsSuperObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/base")
public abstract class AbsSuperController {

    private AbsSuperObject model;

    public AbsSuperObject getModel() {
        return model;
    }

    public void setModel(AbsSuperObject model) {
        this.model = model;
    }

    //定义一个抽象方法调用对应的service是实现类
    public abstract AbsSuperService getBll();


}
