package com.userLogin.repository.mapper;

import com.userLogin.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow (ResultSet rs, int rowNum) throws SQLException {
        return new Order (
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getDate("order_date"),
                rs.getString("shipping_address"),
                rs.getFloat("total_price")
        );
    }
}
