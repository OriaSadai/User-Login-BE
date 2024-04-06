package com.userLogin.service;

import com.userLogin.model.*;
import com.userLogin.model.Order;
import com.userLogin.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public String createOrder(String authorization, OrderRequest orderRequest) throws Exception {
        User user = userService.getUserByAuthorization(authorization);
        Long userId = user.getId();
        Order order = orderRequest.getOrder();
        order.setUserId(userId);

        List<ItemOrder> orderList = orderRequest.getOrderList();

        Integer counter = orderList.size() - 1;
        String errMassage = "";

        for (ItemOrder itemOrder : orderList) {
            Boolean isQuantityAvailable = itemService.checkAvailability(itemOrder.getItemId(), itemOrder.getQuantity());

            try {
                if (!isQuantityAvailable) {
                    Item itemRequest = itemService.getItemByItemId(orderList.get(counter).getItemId());
                    errMassage = String.format("You can't buy more than %s %s's", itemRequest.getInStock(), itemRequest.getName());
                    throw new Exception(errMassage);
                }
            } catch (Exception err) {
                logger.warn(err.getMessage());
                throw new Exception(err.getMessage());
            }
        }

        Long orderId = orderRepository.createOrder(order);

        for (ItemOrder itemOrder : orderList) {
            itemService.updateQuantity(itemOrder);
            orderRepository.addToOrderItemList(orderId, itemOrder);
        }
        return "your order has been placed";
    }

    @Override
    public List<Order> getAllOrdersByUser(String authorization) {
        User user = userService.getUserByAuthorization(authorization);
        return orderRepository.getAllOrderByUserId(user.getId());
    }

    @Override
    public void removeAllOrdersByUser(User user) {
        Long userId = user.getId();
        List<Order> orderUserList = orderRepository.getAllOrdersByUserId(userId);
        for (Order order : orderUserList) {
            orderRepository.removeOrderItemListByOrderId(order.getId());
        }
        orderRepository.removeAllOrdersByUserId(userId);
    }
}
