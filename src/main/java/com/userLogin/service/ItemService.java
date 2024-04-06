package com.userLogin.service;

import com.userLogin.model.Item;
import com.userLogin.model.ItemOrder;
import com.userLogin.model.User;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems();
    List<Item> getAllItemsAuthorized(String authorization);
    List<Item> getItemsByName(String itemName);
    List<Item> getItemsByNameAuthorized(String itemName, String authorization);
    Item getItemByItemId(Integer itemId);
    Item getItemByItemIdAuthorized(Integer itemId, String authorization);
    Boolean checkAvailability(Integer itemId, Integer quantityOnDemand);
    List<Item> getFavoriteItemsByUser(String username);
    void updateQuantity(ItemOrder itemOrder);
}
