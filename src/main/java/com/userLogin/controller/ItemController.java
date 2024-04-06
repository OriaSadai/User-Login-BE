package com.userLogin.controller;

import com.userLogin.model.Item;
import com.userLogin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @GetMapping("/public/get/{id}")
    @CrossOrigin
    public Item getItemById(@PathVariable Integer id) {
        return itemService.getItemByItemId(id);
    }

    @GetMapping(value = "/private/get/{id}")
    @CrossOrigin
    public Item getItemByIdAuthorized(@PathVariable Integer id, @RequestParam(value = "Authorization") String authorization) {
        return itemService.getItemByItemIdAuthorized(id, authorization);
    }

    @GetMapping(value = "/public/get/all")
    @CrossOrigin
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping(value = "/private/get/all")
    @CrossOrigin
    public List<Item> getAllItemsAuthorized(@RequestParam(value = "Authorization") String authorization) {
        return itemService.getAllItemsAuthorized(authorization);
    }

    @GetMapping(value = "/public/get/name/{name}")
    @CrossOrigin
    public List<Item> getItemsByName(@PathVariable(value = "name") String itemName) {
        return itemService.getItemsByName(itemName);
    }

    @GetMapping(value = "/private/get/name/{name}")
    @CrossOrigin
    public List<Item> getItemsByNameAuthorized(@PathVariable(value = "name") String itemName, @RequestParam(value = "Authorization") String authorization) {
        return itemService.getItemsByNameAuthorized(itemName, authorization);
    }

    @GetMapping(value = "/public/get/availability/{id}")
    @CrossOrigin
    public Boolean checkAvailability(@PathVariable Integer itemId, @RequestParam(value = "quantity") Integer quantityOnDemand) {
        return itemService.checkAvailability(itemId, quantityOnDemand);
    }

    @GetMapping(value = "/private/favorite/get")
    @CrossOrigin
    public List<Item> getFavoriteItems(@RequestParam(value = "Authorization") String authorization) {
        return itemService.getFavoriteItemsByUser(authorization);
    }
}
