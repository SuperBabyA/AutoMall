package com.itcase.automall.bll.Impl;

import com.itcase.automall.bll.inter.ICarService;
import com.itcase.automall.dao.inter.ICarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends AbsSuperService implements ICarService {

    @Autowired
    private ICarDao iCarDao;

    @Override
    public ICarDao getDao() {
        return iCarDao;
    }

}
