package com.example.psostest.User.Service;

import com.example.psostest.User.Entity.UsersBasicInfo;
import com.example.psostest.User.Repository.UsersBasicInfoRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UsersService {

    private UsersBasicInfoRepository usersBasicInfoRepository;

    public UsersService(UsersBasicInfoRepository usersBasicInfoRepository) {
        this.usersBasicInfoRepository = usersBasicInfoRepository;
    }

    public UsersBasicInfo getUserBasicInfoByUserId(Integer userId) {
        UsersBasicInfo userInfo = usersBasicInfoRepository.findByUserId(userId);
        if (userId == null) {
            throw new NoSuchElementException();
        }
        return userInfo;

    }
}
