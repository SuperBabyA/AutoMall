package com.itcase.automall.bll.Impl;

import com.itcase.automall.bll.inter.IOrdersService;
import com.itcase.automall.dao.inter.IOrdersDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Orders;
import com.itcase.automall.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl extends AbsSuperService implements IOrdersService {

    @Autowired
    private IOrdersDao iOrdersDao;

    @Override
    public IOrdersDao getDao() {
        return iOrdersDao;
    }

    /*--------------------向Controller暴露的方法--------------------------*/

    //根据所给条件查询用户的订单【userOrderId 用户id、state 状态】
    /*@Override
    public HttpResult findUserOrder(Map<String, Object> cons) {
        HttpResult httpResult = new HttpResult();
        if (cons == null || cons.size() == 0) {
            httpResult.setCode("D0001");
            httpResult.setMessage("查询条件为空，查询失败！");
            httpResult.setObject(-1);
            return httpResult;
        }
        if (getDao() == null) {
            httpResult.setCode("D0002");
            httpResult.setMessage("未获得访问层对象！");
            httpResult.setObject(-1);
            return httpResult;
        }
        List<AbsSuperObject> objects = getDao().findUserOrder(cons);
        if (objects == null) {
            httpResult.setCode("D0003");
            httpResult.setMessage("暂无相应数据！");
            httpResult.setObject(-2);
        } else {
            httpResult.setCode("D0000");
            httpResult.setMessage("已查询出对应数据！");
            httpResult.setObject(objects);
        }
        return httpResult;
    }*/
}
