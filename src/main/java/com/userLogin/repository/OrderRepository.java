package com.userLogin.repository;

import com.userLogin.model.ItemOrder;
import com.userLogin.model.Order;

import java.util.List;

public interface OrderRepository {
    Long createOrder(Order order);
    List<Order> getAllOrderByUserId(Long userId);
    List<ItemOrder> getAllItemsOrderById(Long orderId);
    void addToOrderItemList(Long orderId, ItemOrder itemOrder);
    List<Order> getAllOrdersByUserId(Long userId);
    void removeOrderItemListByOrderId(Long orderId);
    void removeAllOrdersByUserId(Long userId);
}
