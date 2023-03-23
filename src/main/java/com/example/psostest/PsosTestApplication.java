package com.example.psostest;

import com.example.psostest.Event.Repository.EventRepository;
import com.example.psostest.Subject.Repository.SubjectRepository;
import com.example.psostest.Subject.Service.SubjectService;
import com.example.psostest.User.Repository.UserRepository;
import com.example.psostest.User.Repository.UsersBasicInfoRepository;
import com.example.psostest.User.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PsosTestApplication{

    @Autowired
    UserRepository userRepository;
    @Autowired
    UsersBasicInfoRepository usersBasicInfoRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    EventRepository eventRepository;

    @Autowired
    public PsosTestApplication(UserRepository userRepository, UsersBasicInfoRepository usersBasicInfo, UsersService usersService, SubjectRepository subjectRepository, EventRepository eventRepository, SubjectService subjectService) {
        this.userRepository = userRepository;
        this.usersBasicInfoRepository = usersBasicInfo;
        this.subjectRepository = subjectRepository;
        this.eventRepository = eventRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PsosTestApplication.class, args);
    }

}

