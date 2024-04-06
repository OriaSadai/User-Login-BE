package com.userLogin.repository;
import com.userLogin.model.User;
import com.userLogin.repository.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String USER_TABLE_NAME = "USER";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO " + USER_TABLE_NAME + " (first_name, last_name, email, phone_number, address, username, password, roles, permissions) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles(),
                user.getPermissions()
        );
    }
    @Override
    public User findUserByUsername(String username) {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE username=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
        } catch (EmptyResultDataAccessException info) {
            logger.warn(String.format("username \"%s\" does not exist! %s",username, info.getMessage()));
            return null;
        }
    }
    @Override
    public void deleteUserByUserId(Long id) {
        String sql = "DELETE FROM " + USER_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}


