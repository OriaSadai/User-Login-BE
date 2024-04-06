package com.userLogin.model;
public class UserRequest {
    private String username;
    private String password;
    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User toUser() {
        return new User(
                null,
                null,
                null,
                null,
                null,
                null,
                this.username,
                this.password,
                "",
                ""
        );
    }
}
