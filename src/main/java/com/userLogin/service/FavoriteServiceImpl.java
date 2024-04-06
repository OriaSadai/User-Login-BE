package com.userLogin.service;

import com.userLogin.model.Favorite;
import com.userLogin.model.Item;
import com.userLogin.model.User;
import com.userLogin.repository.FavoriteRepository;
import com.userLogin.repository.ItemRepository;
import com.userLogin.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Override
    public Boolean addToFavorite(Integer itemId, String authorization) {
        Item existingItem = itemRepository.getItemByItemId(itemId);
        if (existingItem != null) {
            User existingUser = userService.getUserByAuthorization(authorization);
            return favoriteRepository.addToFavorite(existingItem.getId(), existingUser.getId());
        } else {
            return false;
        }
    }
    @Override
    public void removeFromFavorite(Integer itemId, String authorization) {
        Item existingItem = itemRepository.getItemByItemId(itemId);
        if (existingItem != null) {
            User existingUser = userService.getUserByAuthorization(authorization);
            favoriteRepository.removeFromFavorite(existingItem.getId(), existingUser.getId());
        }
    }
    @Override
    public void removeAllFavoriteItemsByUser(User user) {
        Long userId = user.getId();
        favoriteRepository.removeAllFavoriteItemsByUserId(userId);
    }

    @Override
    public Favorite getFavoriteByItemIdAndUserId(Integer itemId, Long userId) {
        return favoriteRepository.getFavoriteByItemIdAndUserId(itemId, userId);
    }

}
