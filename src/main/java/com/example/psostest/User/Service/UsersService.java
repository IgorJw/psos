package com.example.psostest.User.Service;

import com.example.psostest.User.Repository.UsersBasicInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersService {

    private UsersBasicInfoRepository usersBasicInfoRepository;

}
