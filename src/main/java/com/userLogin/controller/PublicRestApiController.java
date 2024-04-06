package com.userLogin.controller;

import com.userLogin.model.TestResponse;
import com.userLogin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/private")
public class PublicRestApiController {
    @Autowired
    private ItemService itemService;
    @CrossOrigin
    @GetMapping("/test1")
    public ResponseEntity<?> test1() {
        return ResponseEntity.ok("API Test 1");
    }
    @CrossOrigin
    @GetMapping("/test2")
    public ResponseEntity<?> test3(){
        return ResponseEntity.ok(new TestResponse("API Test 2"));
    }
}


