package com.itcase.automall.bll.Impl;

import com.itcase.automall.bll.inter.ICollectionService;
import com.itcase.automall.dao.inter.ICollectionDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.Collection;
import com.itcase.automall.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl extends AbsSuperService implements ICollectionService {

    private Collection model;

    @Override
    public Collection getModel() {
        return model;
    }

    public void setModel(Collection model) {
        this.model = model;
    }

    @Autowired
    private ICollectionDao iCollectionDao;

    @Override
    public ICollectionDao getDao() {
        return iCollectionDao;
    }

    /*--------------------向Controller暴露的方法--------------------------*/

    //查看单个用户的收藏列表
    @Override
    public HttpResult findUserCollection() {
        HttpResult httpResult = new HttpResult();
        if (model == null ||
                model.getUserCollectionId() == null ||
                "".equals(model.getUserCollectionId())) {
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
        List<AbsSuperObject> objects = getDao().findUserCollection(model.getUserCollectionId());
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
    }
}
