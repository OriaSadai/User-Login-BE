package com.userLogin.controller;
import com.userLogin.model.User;
import com.userLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/public/create")
    @CrossOrigin
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
           userService.createUser(user);
           return null;
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/private/get/address")
    @CrossOrigin
    public String getUserAddress(@RequestParam(value = "Authorization") String authorization) {
        return userService.getUserAddress(authorization);
    }

    @DeleteMapping(value = "/private/delete")
    @CrossOrigin
    public void deleteUser(@RequestParam(value = "Authorization") String authorization) throws Exception {
        userService.removeUser(authorization);
    }
}



