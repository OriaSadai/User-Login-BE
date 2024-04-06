package com.userLogin.repository;

import com.userLogin.model.Favorite;
import com.userLogin.model.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> getAllItems();
    List<Item> getItemsByName(String itemName);
    Item getItemByItemId(Integer itemId);

    List<Item> getFavoriteItemsByUserId(Long userId);
    void updateQuantity(Integer quantity, Integer itemId);
}
