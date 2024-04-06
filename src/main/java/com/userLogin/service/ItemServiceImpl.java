package com.userLogin.service;

import com.userLogin.model.Favorite;
import com.userLogin.model.Item;
import com.userLogin.model.ItemOrder;
import com.userLogin.model.User;
import com.userLogin.repository.ItemRepository;
import com.userLogin.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Override
    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }

    @Override
    public List<Item> getAllItemsAuthorized(String authorization) {
        User userRequested = userService.getUserByAuthorization(authorization);
        Long userId = userRequested.getId();
        List<Item> itemList = getAllItems();

        for (Item itemInList : itemList) {
            Favorite favoriteItem = favoriteService.getFavoriteByItemIdAndUserId(itemInList.getId(), userId);
            if (favoriteItem != null) {
                itemInList.setIsFavorite(true);
            }
        }

        return itemList;
    }

    @Override
    public List<Item> getItemsByName(String itemName) {
        return itemRepository.getItemsByName(itemName);
    }

    @Override
    public List<Item> getItemsByNameAuthorized(String itemName, String authorization) {
        User userRequested = userService.getUserByAuthorization(authorization);
        Long userId = userRequested.getId();
        List<Item> itemList = getItemsByName(itemName);

        for (Item itemInList : itemList) {
            Favorite favoriteItem = favoriteService.getFavoriteByItemIdAndUserId(itemInList.getId(), userId);
            if (favoriteItem != null) {
                itemInList.setIsFavorite(true);
            }
        }

        return itemList;
    }

    @Override
    public Item getItemByItemId(Integer itemId) {
        return itemRepository.getItemByItemId(itemId);
    }

    @Override
    public Item getItemByItemIdAuthorized(Integer itemId, String authorization) {
        Item itemRequested = itemRepository.getItemByItemId(itemId);
        User userRequest = userService.getUserByAuthorization(authorization);
        Favorite favoriteItem = favoriteService.getFavoriteByItemIdAndUserId(itemId, userRequest.getId());
        if (favoriteItem != null) {
            itemRequested.setIsFavorite(true);
        }
        return itemRequested;
    }

    @Override
    public Boolean checkAvailability(Integer itemId, Integer quantityOnDemand) {
        Item item = getItemByItemId(itemId);
        return ( item.getInStock() >= quantityOnDemand ) ? true : false;
    }

    @Override
    public List<Item> getFavoriteItemsByUser(String authorization) {
        User existingUser = userService.getUserByAuthorization(authorization);
        List<Item> favoriteList = itemRepository.getFavoriteItemsByUserId(existingUser.getId());
        for (Item itemInFavoriteList : favoriteList) {
            itemInFavoriteList.setIsFavorite(true);
        }
        return favoriteList;
    }

    @Override
    public void updateQuantity(ItemOrder itemOrder) {
        Item itemToUpdate = itemRepository.getItemByItemId(itemOrder.getItemId());
        Integer itemId = itemToUpdate.getId();
        Integer quantityInStock = itemToUpdate.getInStock();
        Integer quantityDemand = itemOrder.getQuantity();
        if (quantityInStock >= quantityDemand) {
            itemRepository.updateQuantity(quantityInStock - quantityDemand, itemId);
        } else {
            logger.error(String.format("quantity demand not in stock"));
        }
    }
}