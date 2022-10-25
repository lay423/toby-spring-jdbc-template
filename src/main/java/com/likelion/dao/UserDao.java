package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {
        ConnectionMaker conn;

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

    public void deleteAll() {
        try{
            Connection c = conn.makeConnection();

            PreparedStatement pstmt = c.prepareStatement("DELETE FROM users");

            pstmt.executeUpdate();

            pstmt.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCount() {
        String s;
        try{
            Connection c = conn.makeConnection();

            PreparedStatement pstmt = c.prepareStatement("SELECT count(id) FROM users");

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            s = rs.getString("count(id)");

            rs.close();
            pstmt.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
