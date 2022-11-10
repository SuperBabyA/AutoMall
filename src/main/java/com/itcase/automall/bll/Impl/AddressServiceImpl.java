package com.itcase.automall.bll.Impl;

import com.itcase.automall.bll.inter.IAddressService;
import com.itcase.automall.dao.inter.IAddressDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Address;
import com.itcase.automall.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends AbsSuperService implements IAddressService {

    private Address model;

    @Override
    public Address getModel() {
        return model;
    }

    public void setModel(Address model) {
        this.model = model;
    }

    @Autowired
    private IAddressDao iAddressDao;

    @Override
    public IAddressDao getDao() {
        return iAddressDao;
    }

    /*---------------------向Controller暴露的方法-----------------------------*/

    //根据user_address_id字段查询单个用户的所有地址【用户或管理员进行个人地址查询】
    @Override
    public HttpResult findUserAddress() {
        HttpResult httpResult = new HttpResult();
        if (model == null ||
                model.getUserAddressId() == null ||
                "".equals(model.getUserAddressId())) {
            httpResult.setCode("D0001");
            httpResult.setMessage("对象为空，查询失败！");
            httpResult.setData(-1);
            return httpResult;
        }
        if (getDao() == null) {
            httpResult.setCode("D0002");
            httpResult.setMessage("未获得访问层对象！");
            httpResult.setData(-1);
            return httpResult;
        }
        List<AbsSuperObject> objects = getDao().findUserAddress(model.getUserAddressId());
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

    //根据user_address_id和订单id修改用户地址【用户或管理员进行地址修改】
    /*@Override
    public HttpResult modifyUserAddress() {
        HttpResult httpResult = new HttpResult();
        if (model == null) {
            httpResult.setCode("U0001");
            httpResult.setMessage("对象或id为空，保存异常！");
            httpResult.setObject(-1);
            return httpResult;
        }
        if (getDao() == null) {
            httpResult.setCode("U0002");
            httpResult.setMessage("未获得当前访问层对象！");
            httpResult.setObject(-1);
            return httpResult;
        }
        try {
            int result = getDao().modifyUserAddress(model);
            if (result > 0) {
                httpResult.setCode("U0000");
                httpResult.setMessage("修改成功！");
                httpResult.setObject(result);
            } else {
                httpResult.setCode("U0003");
                httpResult.setMessage("添加失败！");
                httpResult.setObject(-1);
            }
            return httpResult;
        } catch (DuplicateKeyException e) { //捕获插入或更新数据导致违反主键或唯一约束时引发异常
            httpResult.setCode("U0004");
            httpResult.setMessage("该名称已存在！");
            httpResult.setObject(-1);
            return httpResult;
        } catch (DataIntegrityViolationException e) {   //捕获插入或更新数据导致违反外键或唯一约束时引发异常
            httpResult.setCode("U0005");
            httpResult.setMessage("插入数据关联的数据不存在，添加失败！");
            httpResult.setObject(-1);
            return httpResult;
        }
    }*/
}
