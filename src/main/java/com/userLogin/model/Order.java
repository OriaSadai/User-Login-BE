package com.userLogin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Order {
    private Long id;
    private Long userId;
    private Date orderDate;
    @JsonProperty(value = "address")
    private String shippingAddress;
    @JsonProperty(value = "price")
    private Float totalPrice;
    public Order(Long id, Long userId, Date orderDate, String shippingAddress, Float totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
    }
    public Order() {}
    public Long getId() {
        return this.id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public Date getOrderDate() {
        return this.orderDate;
    }
    public String getShippingAddress() {
        return this.shippingAddress;
    }
    public Float getTotalPrice() {
        return this.totalPrice;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
