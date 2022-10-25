package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;
    UserDao userDao;
    User user1;
    User user2;
    User user3;
    User emptyUser;
    @BeforeEach
    public void setUp(){
        userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        user1 = new User("1", "Rara", "1123");
        user2 = new User("2", "Rara", "1123");
        user3 = new User("3", "Rara", "1123");
    }

    @Test
    @DisplayName("Add와 findById 테스트")
    public void addAndFindById() {

        assertEquals(userDao.getCount(), "0");

        userDao.add(user1);
        assertThrows(NullPointerException.class, () ->{
            userDao.add(emptyUser);
        });

        User user = userDao.findById("1");
        assertEquals(user.getName(), "Rara");

        assertEquals(userDao.getCount(), "1");
        userDao.add(user2);
        assertEquals(userDao.getCount(), "2");
        userDao.add(user3);
        assertEquals(userDao.getCount(), "3");
    }
}