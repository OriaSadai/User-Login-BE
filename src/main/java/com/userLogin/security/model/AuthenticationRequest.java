package com.userLogin.security.model;

import java.io.Serializable;
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }
    public AuthenticationRequest() {} //need default constructor for JSON Parsing
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
