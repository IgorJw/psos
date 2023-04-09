package com.example.psostest;

import com.example.psostest.Event.Repository.EventRepository;
import com.example.psostest.Subject.Repository.SubjectRepository;
import com.example.psostest.Subject.Service.SubjectService;
import com.example.psostest.User.Repository.UserRepository;
import com.example.psostest.User.Repository.UsersBasicInfoRepository;
import com.example.psostest.User.Service.UsersService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
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
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(PsosTestApplication.class, args);
    }

}

