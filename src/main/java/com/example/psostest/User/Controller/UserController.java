package com.example.psostest.User.Controller;

import com.example.psostest.Shared.Response.ResponseWithMessage;
import com.example.psostest.User.Entity.UsersBasicInfo;
import com.example.psostest.User.Repository.UsersBasicInfoRepository;
import com.example.psostest.User.Request.UsersBasicInfoModifyRequest;
import com.example.psostest.User.Service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UsersBasicInfoRepository usersBasicInfoRepository;
    private final UsersService usersService;

    @GetMapping("/user/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Map<String, String>> getUserInfo(@PathVariable Integer userId) {
        Map<String, String> map = new HashMap<>();
        UsersBasicInfo userInfo = usersBasicInfoRepository.findByUserId(userId);

        map.put("name", userInfo.getName());
        map.put("surname", userInfo.getSurname());
        map.put("field_of_study", userInfo.getField_of_study());
        map.put("year", userInfo.getYear().toString());

        return ResponseEntity.ok(map);
    }

    @PutMapping("/user")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ResponseWithMessage> updateUserBasicInfo(@RequestBody UsersBasicInfoModifyRequest request) {
        try {
            UsersBasicInfo usersBasicInfo = usersBasicInfoRepository.findByUserId(request.getId());

            if (usersBasicInfo == null) {
                throw new NoSuchElementException();
            }

            if (request.getName() != null) {
                usersBasicInfo.setName(request.getName());
            }
            if (request.getSurname() != null) {
                usersBasicInfo.setSurname(request.getSurname());
            }
            if (request.getYear() != null) {
                usersBasicInfo.setYear(request.getYear());
            }

            usersBasicInfoRepository.save(usersBasicInfo);
            return ResponseEntity.ok(new ResponseWithMessage("UserBasicInfo modified"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWithMessage("UserBasicInfo to modify not found"));
        }
    }
}
