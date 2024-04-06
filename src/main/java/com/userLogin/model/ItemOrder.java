package com.userLogin.model;

public class ItemOrder {
    private Integer itemId;
    private Integer quantity;
    public ItemOrder(Integer itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }
    public ItemOrder() {
    }
    public Integer getItemId() {
        return itemId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
