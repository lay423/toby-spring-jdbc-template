package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void setUp(){

    }

    @Test
    @DisplayName("Add와 findById 테스트")
    public void addAndFindById() {
        UserDao userDao = new UserDaoFactory().awsUserDao();
        String id = "11";
        userDao.add(new User(id, "Rara", "1123"));
        User user = userDao.findById(id);
        Assertions.assertEquals(user.getName(), "Rara");
    }
}