package com.itcase.automall;

import com.itcase.automall.dao.inter.IUserDao;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
class AutomallApplicationTests {

    @Autowired
    private User user;

    @Autowired
    private IUserDao iUserDao ;

    @Test
    void contextLoads() {
        log.info("Test:{}",iUserDao);
        List<AbsSuperObject> users = iUserDao.findAllObj();
        users.forEach(System.out::println);
    }
}
