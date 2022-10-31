package com.itcase.automall.bll.Impl;

import com.itcase.automall.bll.inter.IReservationService;
import com.itcase.automall.dao.inter.IReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl extends AbsSuperService implements IReservationService {

    @Autowired
    private IReservationDao iReservationDao;

    @Override
    public IReservationDao getDao() {
        return iReservationDao;
    }
}
