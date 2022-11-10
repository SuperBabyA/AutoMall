package com.itcase.automall.bll.Impl;

import com.itcase.automall.bll.inter.IDetailService;
import com.itcase.automall.dao.inter.IDetailDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Detail;
import com.itcase.automall.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailServiceImpl extends AbsSuperService implements IDetailService {

    private Detail model;

    @Override
    public Detail getModel() {
        return model;
    }

    public void setModel(Detail model) {
        this.model = model;
    }

    @Autowired
    private IDetailDao iDetailDao;

    @Override
    public IDetailDao getDao() {
        return iDetailDao;
    }


    /*-----------------向Controller暴露的方法---------------------*/

    //查询单个汽车的详细信息
    @Override
    public HttpResult findByIdCarAllInfo() {
        HttpResult httpResult = new HttpResult();
        if (model == null) {
            httpResult.setCode("D0001");
            httpResult.setMessage("对象为空，查询失败！");
            httpResult.setData(-1);
        }
        if (getDao() == null) {
            httpResult.setCode("D0002");
            httpResult.setMessage("未获得访问层对象！");
            httpResult.setData(-1);
        }
        List<AbsSuperObject> objects = getDao().findByIdCarAllInfo(model.getCarDetailId());
        if (objects == null) {
            httpResult.setCode("D0003");
            httpResult.setMessage("暂无相应数据！");
            httpResult.setData(-2);
        } else {
            httpResult.setCode("D0000");
            httpResult.setMessage("已查询出对应数据！");
            httpResult.setData(objects);
        }
        return httpResult;
    }
}
