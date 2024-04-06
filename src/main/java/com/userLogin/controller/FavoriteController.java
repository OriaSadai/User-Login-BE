package com.userLogin.controller;

import com.userLogin.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @PostMapping(value = "/private/add")
    @CrossOrigin
    public Boolean addToFavorite(@RequestParam(value = "itemId") Integer itemId, @RequestParam(value = "Authorization") String authorization) {
        return favoriteService.addToFavorite(itemId, authorization);
    }
    @DeleteMapping(value = "/private/remove")
    @CrossOrigin
    public void removeFromFavorite(@RequestParam(value = "itemId") Integer itemId, @RequestParam(value = "Authorization") String authorization) {
        favoriteService.removeFromFavorite(itemId, authorization);
    }
}
