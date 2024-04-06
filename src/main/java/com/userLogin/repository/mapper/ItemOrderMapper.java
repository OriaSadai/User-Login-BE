package com.userLogin.repository.mapper;

import com.userLogin.model.ItemOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemOrderMapper implements RowMapper<ItemOrder> {
    @Override
    public ItemOrder mapRow(ResultSet rs, int i) throws SQLException {
        return new ItemOrder(
                rs.getInt("itemId"),
                rs.getInt("quantity")
        );
    }
}
