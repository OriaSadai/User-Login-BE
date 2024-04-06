package com.userLogin.service;

import com.userLogin.model.User;

public interface UserService {
    void createUser(User user) throws Exception;
    User getUserByAuthorization (String authorization);
    User findUserByUsername(String username);
    String getUserAddress (String authorization);
    void removeUser(String authorization);
}
