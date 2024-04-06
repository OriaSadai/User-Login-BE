package com.userLogin.repository;

import com.userLogin.model.Item;
import com.userLogin.repository.mapper.ItemMapper;
import com.userLogin.repository.mapper.MiniItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private static final String ITEM_TABLE_NAME = "item";
    private static final String FAVORITE_TABLE_LIST = "favorite_list";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private MiniItemMapper miniItemMapper;
    private static final Logger logger = LoggerFactory.getLogger(ItemRepositoryImpl.class);

    @Override
    public List<Item> getAllItems() throws EmptyResultDataAccessException {
        String sql = "SELECT id, name, small_url_picture, price FROM " + ITEM_TABLE_NAME + "";
        try {
            return jdbcTemplate.query(sql, miniItemMapper);
        } catch (EmptyResultDataAccessException err) {
            logger.warn("ITEM LIST IS EMPTY! " + err.getMessage());
            return null;
        }
    }

    @Override
    public List<Item> getItemsByName(String itemName) throws EmptyResultDataAccessException {
        String sql = "SELECT id, name, small_url_picture, price FROM " + ITEM_TABLE_NAME + " WHERE NAME LIKE ?";
        try {
            return jdbcTemplate.query(sql, miniItemMapper, "%" + itemName + "%");
        } catch (EmptyResultDataAccessException err) {
            logger.info(String.format("item with name \"%s\" does NOT exist. %s",itemName, err.getMessage()));
            return null;
        }
    }

    @Override
    public Item getItemByItemId(Integer itemId) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, itemMapper, itemId);
        } catch (EmptyResultDataAccessException err) {
            logger.error(String.format("item id \"%s\" not exist. %s",itemId, err.getMessage()));
            return null;
        }
    }

    @Override
    public List<Item> getFavoriteItemsByUserId(Long userId) throws EmptyResultDataAccessException {
        String sql = "SELECT id, name, small_url_picture, price  FROM " + ITEM_TABLE_NAME + " AS I WHERE I.ID IN (SELECT FL.ITEM_ID FROM " + FAVORITE_TABLE_LIST + " AS FL WHERE FL.USER_ID=?)";
        try {
            return jdbcTemplate.query(sql, miniItemMapper, userId);
        } catch (EmptyResultDataAccessException err) {
            logger.info(String.format("user with id \"%s\" has no favorite items. %s",userId, err.getMessage()));
            return null;
        }
    }

    @Override
    public void updateQuantity(Integer quantity, Integer itemId) {
        String sql = "UPDATE " + ITEM_TABLE_NAME + " SET in_stock=? WHERE ID=?";
        try {
            jdbcTemplate.update(
                    sql,
                    quantity,
                    itemId
            );
        } catch (Exception err) {
            logger.error(String.format("wrong quantity \"%s\", %s", quantity, err.getMessage()));
        }
    }
}
