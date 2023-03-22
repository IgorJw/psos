package com.example.psostest.User.Controller;

import com.example.psostest.User.Entity.UsersBasicInfo;
import com.example.psostest.User.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/user/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Map<String, String>> getUserInfo(@PathVariable Integer userId) {
        Map<String, String> map = new HashMap<>();
        UsersBasicInfo userInfo = usersService.getUserBasicInfoByUserId(userId);

        map.put("name", userInfo.getName());
        map.put("surname", userInfo.getSurname());
        map.put("year", userInfo.getYear().toString());

        return ResponseEntity.ok(map);
    }
}
