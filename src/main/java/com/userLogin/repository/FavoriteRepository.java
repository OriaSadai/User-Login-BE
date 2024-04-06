package com.userLogin.repository;

import com.userLogin.model.Favorite;
import com.userLogin.repository.mapper.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

public interface FavoriteRepository {
    Boolean addToFavorite(Integer itemId, Long userId);
    void removeFromFavorite(Integer itemId, Long userId);
    void removeAllFavoriteItemsByUserId(Long userId);
    Favorite getFavoriteByItemIdAndUserId(Integer itemId, Long UserId);
}
