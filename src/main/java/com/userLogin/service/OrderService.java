package com.userLogin.service;

import com.userLogin.model.Order;
import com.userLogin.model.OrderRequest;
import com.userLogin.model.User;

import java.util.List;


public interface OrderService {
    String createOrder(String authorization, OrderRequest orderRequest) throws Exception;
    List<Order> getAllOrdersByUser(String authorization);
    void removeAllOrdersByUser(User user);
}
