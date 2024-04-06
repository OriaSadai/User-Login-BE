package com.userLogin.repository.mapper;

import com.userLogin.model.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow (ResultSet rs, int rowNum) throws SQLException {
        return new Item(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("small_url_picture"),
                rs.getString("big_url_picture"),
                rs.getInt("in_stock"),
                rs.getFloat("price"),
                false
        );
    }
}
