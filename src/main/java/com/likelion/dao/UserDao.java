package com.likelion.dao;

import com.likelion.domain.User;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class UserDao {
        ConnectionMaker conn;

    public UserDao(ConnectionMaker conn) {
        this.conn = conn;
    }

    public void jdbcContextStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = conn.makeConnection();
        PreparedStatement pstmt = stmt.makeStrategy(c);
        pstmt.executeUpdate();

        pstmt.close();
        c.close();
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

    public void deleteAll() throws SQLException {

        StatementStrategy stmt = new StatementStrategy() {
            @Override
            public PreparedStatement makeStrategy(Connection connection) throws SQLException {
                return connection.prepareStatement("delete from users");
            }
        };
        jdbcContextStatementStrategy(stmt);
    }

    public String getCount() throws SQLException {
        StatementStrategy stmt = (connection -> {
            return connection.prepareStatement("select count(*) from users");
        });
        String s = null;
        try {
            PreparedStatement pstmt = stmt.makeStrategy(conn.makeConnection());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            s = rs.getString(1);
            rs.close();
            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
