package com.userLogin.repository.mapper;

import com.userLogin.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow (ResultSet rs, int rowNum) throws SQLException {
        return new User (
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getString("address"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("roles"),
                rs.getString("permissions")
        );
    }
}
