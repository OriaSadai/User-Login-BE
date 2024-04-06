package com.userLogin.repository.mapper;

import com.userLogin.model.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class MiniItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int i) throws SQLException {
        return new Item (
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("small_url_picture"),
                rs.getFloat("price"),
                false
        );
    }
}
