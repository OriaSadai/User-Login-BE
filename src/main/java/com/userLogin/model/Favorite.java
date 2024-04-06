package com.userLogin.model;

public class Favorite {
    private Integer itemId;
    private Long userId;
    public Favorite(Integer itemId, Long userId) {
        this.itemId = itemId;
        this.userId = userId;
    }
    public Favorite() {
    }
    public Integer getItemId() {
        return itemId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
