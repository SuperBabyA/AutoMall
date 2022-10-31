package com.itcase.automall.bll.Impl;

import com.itcase.automall.bll.inter.IFeedbackService;
import com.itcase.automall.dao.inter.IFeedbackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl extends AbsSuperService implements IFeedbackService {

    @Autowired
    private IFeedbackDao iFeedbackDao;

    @Override
    public IFeedbackDao getDao() {
        return iFeedbackDao;
    }


}
