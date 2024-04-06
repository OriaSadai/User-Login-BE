package com.userLogin.service;

import com.userLogin.model.Favorite;
import com.userLogin.model.User;

public interface FavoriteService {
    Boolean addToFavorite(Integer itemId, String username);
    void removeFromFavorite(Integer itemId, String username);
    void removeAllFavoriteItemsByUser(User user);
    Favorite getFavoriteByItemIdAndUserId(Integer itemId, Long userId);
}
