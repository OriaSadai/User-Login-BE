package com.userLogin.repository;

import com.userLogin.model.ItemOrder;
import com.userLogin.model.Order;
import com.userLogin.repository.mapper.ItemOrderMapper;
import com.userLogin.repository.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private static final String ORDER_LIST_TABLE_NAME = "order_list";
    private static final String ORDER_ITEM_LIST_TABLE_NAME = "order_item_list";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    @Override
    public Long createOrder(Order order) {
        String sql = "INSERT INTO " + ORDER_LIST_TABLE_NAME + " (user_id, shipping_address, total_price) VALUES (?, ?, ?)";
        jdbcTemplate.update (
                sql,
                order.getUserId(),
                order.getShippingAddress(),
                order.getTotalPrice()
        );
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", Long.class);
    }
    @Override
    public List<Order> getAllOrderByUserId(Long userId) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM " + ORDER_LIST_TABLE_NAME + " WHERE USER_ID=?";
        try {
            return jdbcTemplate.query(sql, new OrderMapper(), userId);
        } catch (EmptyResultDataAccessException err) {
            logger.error(String.format("no order by id \"%s\". %s",userId, err.getMessage()));
            return null;
        }
    }
    @Override
    public List<ItemOrder> getAllItemsOrderById(Long orderId) throws EmptyResultDataAccessException {
        String sql = "SELECT FROM " + ORDER_ITEM_LIST_TABLE_NAME + " WHERE ORDER_ID=?";
        try {
            return jdbcTemplate.query(sql, new ItemOrderMapper(), orderId);
        } catch (EmptyResultDataAccessException err) {
            logger.error(String.format("no items in order id \"%s\". %s",orderId, err.getMessage()));
            return null;
        }
    }

    @Override
    public void addToOrderItemList(Long orderId, ItemOrder itemOrder) {
        String sql = "INSERT INTO " + ORDER_ITEM_LIST_TABLE_NAME + " (ORDER_ID, ITEM_ID, QUANTITY) VALUES (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                orderId,
                itemOrder.getItemId(),
                itemOrder.getQuantity()
        );
    }
    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        String sql = "SELECT * FROM " + ORDER_LIST_TABLE_NAME + " WHERE USER_ID=?";
        try {
            return jdbcTemplate.query(sql, new OrderMapper(), userId);
        } catch (EmptyResultDataAccessException err) {
            logger.error(String.format("no orders for user id \"%s\". %s",userId, err.getMessage()));
            return null;
        }
    }
    @Override
    public void removeOrderItemListByOrderId(Long orderId) {
        String sql = "DELETE FROM " + ORDER_ITEM_LIST_TABLE_NAME + " WHERE ORDER_ID=?";
        jdbcTemplate.update(sql, orderId);
    }
    @Override
    public void removeAllOrdersByUserId(Long userId) {
        String sql = "DELETE FROM " + ORDER_LIST_TABLE_NAME + " WHERE USER_ID=?";
        jdbcTemplate.update(sql, userId);
    }
}
