package com.userLogin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderRequest {
    @JsonProperty(value = "Authorization")
    private String authorization;
    private Order order;
    @JsonProperty(value = "list")
    private List<ItemOrder> orderList;
    public OrderRequest(String authorization, Order order, List<ItemOrder> orderList) {
        this.authorization = authorization;
        this.order = order;
        this.orderList = orderList;
    }
    public OrderRequest() {}
    public String getAuthorization() {
        return authorization;
    }
    public Order getOrder() {
        return order;
    }
    public List<ItemOrder> getOrderList() {
        return orderList;
    }
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public void setOrderList(List<ItemOrder> orderList) {
        this.orderList = orderList;
    }
}
