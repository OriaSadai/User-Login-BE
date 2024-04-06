package com.userLogin.repository.mapper;

import com.userLogin.model.Favorite;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FavoriteMapper implements RowMapper<Favorite> {
    @Override
    public Favorite mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Favorite (
                resultSet.getInt("item_id"),
                resultSet.getLong("user_id")
        );
    }
}
