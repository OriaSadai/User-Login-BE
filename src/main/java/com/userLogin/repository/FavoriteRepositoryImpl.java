package com.userLogin.repository;

import com.userLogin.model.Favorite;
import com.userLogin.repository.mapper.FavoriteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository {
    private static final String FAVORITE_TABLE_LIST = "favorite_list";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FavoriteMapper favoriteMapper;
    private static final Logger logger = LoggerFactory.getLogger(ItemRepositoryImpl.class);
    @Override
    public Boolean addToFavorite(Integer itemId, Long userId) {
        String sql = "INSERT INTO " + FAVORITE_TABLE_LIST + " " + "(item_id, user_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, itemId, userId);
            return true;
        } catch (Error err) {
            logger.error(String.format("fail ad to favorite. item id \"%s\". user id \"%s\". %s", itemId, userId, err.getMessage()));
            return false;
        }
    }
    @Override
    public void removeFromFavorite(Integer itemId, Long userId) {
        String sql = "DELETE FROM " + FAVORITE_TABLE_LIST + " WHERE ITEM_ID=? AND USER_ID=?";
        try {
            jdbcTemplate.update(sql, itemId, userId);
        } catch (Error err) {
            logger.error("fail remove from favorite. item id \"%s\". user id \"%s\". %s",itemId,userId, err.getMessage());
        }
    }

    @Override
    public void removeAllFavoriteItemsByUserId(Long userId) {
        String sql = "DELETE FROM " + FAVORITE_TABLE_LIST + " WHERE USER_ID=?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public Favorite getFavoriteByItemIdAndUserId(Integer itemId, Long userId) {
        String sql = "SELECT * FROM " + FAVORITE_TABLE_LIST + " WHERE ITEM_ID=? AND USER_ID=?";
        try {
            return jdbcTemplate.queryForObject(sql, favoriteMapper, itemId, userId);
        } catch (EmptyResultDataAccessException err) {
            logger.warn(String.format("no favorites for user id\"%s\". %s",userId, err.getMessage()));
            return null;
        }
    }

}
