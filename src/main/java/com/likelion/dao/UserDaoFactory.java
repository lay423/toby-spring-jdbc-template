package com.likelion.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserDaoFactory {

    public UserDao awsUserDao(){
        return new UserDao(new AwsConnectionMaker());
    }

    public UserDao localUserDao(){
        return new UserDao(new LocalConnectionMaker());
    }
}
