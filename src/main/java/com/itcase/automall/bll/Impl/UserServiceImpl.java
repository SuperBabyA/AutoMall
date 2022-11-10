package com.itcase.automall.bll.Impl;

import com.itcase.automall.bll.inter.IUserService;
import com.itcase.automall.dao.inter.IUserDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.User;
import com.itcase.automall.utils.HttpResult;
import com.itcase.automall.utils.encryption.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;

@Service
public class UserServiceImpl extends AbsSuperService implements IUserService {

    private User model;

    @Override
    public User getModel() {
        return model;
    }

    public void setModel(User model) {
        this.model = model;
    }

    @Autowired
    private IUserDao iUserDao;

    @Override
    public IUserDao getDao() {
        return iUserDao;
    }

    //根据account查询对象
    public HttpResult findByEmail(Map<String, Object> cons) {
        HttpResult httpResult = new HttpResult();
        if (cons == null ||
                cons.size() == 0 ||
                cons.get("email") == null ||
                cons.get("password") == null) {
            httpResult.setCode("D0001");
            httpResult.setMessage("未填写完全登录信息，登录失败！");
            httpResult.setData(-1);
            return httpResult;
        }
        if (getDao() == null) {
            httpResult.setCode("D0002");
            httpResult.setMessage("未获得访问层对象！");
            httpResult.setData(-1);
            return httpResult;
        }
        AbsSuperObject object = getDao().findByEmail(cons);      //调用dao接口方法

        if (object == null) {
            httpResult.setCode("D0003");
            httpResult.setMessage("暂无相应数据！");
            httpResult.setData(-2);
        } else {
            User user = (User) object;
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            user.setToken(MD5Util.formPassToDBPass(String.valueOf(timeInMillis), "12abCD##"));
            getDao().updateObj(user);
            httpResult.setCode("D0000");
            httpResult.setMessage("登录成功！");
            httpResult.setData(user);
        }
        return httpResult;
    }
}
