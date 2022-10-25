package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

        ConnectionMaker conn;

    public UserDao() {
        this.conn = new AwsConnectionMaker();
    }

    public UserDao(ConnectionMaker conn) {
        this.conn = conn;
    }

    public void add(User user) {
        try{
            Connection c = conn.makeConnection();

            PreparedStatement pstmt = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?)");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());

            pstmt.executeUpdate();

            pstmt.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {
        try{
            Connection c = conn.makeConnection();

            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM users WHERE id=?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));

            rs.close();
            pstmt.close();
            c.close();

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = userDao.findById("1");
        System.out.println(user.getName());

    }
}
