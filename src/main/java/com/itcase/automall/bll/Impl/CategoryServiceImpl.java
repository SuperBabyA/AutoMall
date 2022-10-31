package com.itcase.automall.bll.Impl;

import com.itcase.automall.bll.inter.ICategoryService;
import com.itcase.automall.dao.inter.ICategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbsSuperService implements ICategoryService {

    @Autowired
    private ICategoryDao iCategoryDao;

    @Override
    public ICategoryDao getDao() {
        return iCategoryDao;
    }
}
