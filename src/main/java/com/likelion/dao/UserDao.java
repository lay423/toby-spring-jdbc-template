package com.likelion.dao;

import com.likelion.domain.User;
import com.mysql.cj.protocol.Resultset;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDao {

        private JdbcTemplate jdbcTemplate;

    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("password")
            );
            return user;
        }
    };
    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void add(User user) {
        this.jdbcTemplate.update("insert into users(id, name, password) values (?, ?, ?)",
                user.getId(), user.getName(), user.getPassword());
    }

    public User findById(String id) {
        String sql = "select * from users where id = ?";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void deleteAll() throws SQLException {
        this.jdbcTemplate.update("delete from users");
    }

    public String getCount() throws SQLException {
        return this.jdbcTemplate.queryForObject("select count(*) from users", String.class);
    }

    public List<User> getAll() {
        String sql = "select * from users order by id";
        return this.jdbcTemplate.query(sql, rowMapper);
    }
}
