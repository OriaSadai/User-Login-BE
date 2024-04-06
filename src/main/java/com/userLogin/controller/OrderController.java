package com.userLogin.controller;

import com.userLogin.model.Order;
import com.userLogin.model.OrderRequest;
import com.userLogin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/order/private")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping(value = "/create")
    @CrossOrigin
    public String createOrder(@RequestParam(value = "Authorization") String authorization, @RequestBody OrderRequest orderRequest) throws Exception {
        return orderService.createOrder(authorization, orderRequest);
    }

    @PostMapping(value = "/get")
    @CrossOrigin
    public List<Order> getAllOrdersByUser(@RequestParam(value = "Authorization") String authorization) {
        return orderService.getAllOrdersByUser(authorization);
    }
}
