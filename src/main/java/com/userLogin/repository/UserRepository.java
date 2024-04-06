package com.userLogin.repository;

import com.userLogin.model.User;

public interface UserRepository {
    void createUser(User user);
    User findUserByUsername(String username);
    void deleteUserByUserId(Long id);
}
