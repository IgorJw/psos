package com.example.psostest.User.Repository;

import com.example.psostest.User.Entity.UsersBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersBasicInfoRepository extends JpaRepository<UsersBasicInfo,Integer> {
    UsersBasicInfo findByUserId(Integer userId);
}
