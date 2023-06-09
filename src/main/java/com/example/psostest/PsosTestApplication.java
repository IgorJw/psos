package com.example.psostest;

import com.example.psostest.Event.Repository.EventRepository;
import com.example.psostest.Storage.Service.StorageService;
import com.example.psostest.Subject.Repository.SubjectRepository;
import com.example.psostest.User.Repository.UserRepository;
import com.example.psostest.User.Repository.UsersBasicInfoRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@RequiredArgsConstructor
public class PsosTestApplication implements CommandLineRunner {

    final UserRepository userRepository;

    final UsersBasicInfoRepository usersBasicInfoRepository;

    final SubjectRepository subjectRepository;

    final EventRepository eventRepository;

    final StorageService storageService;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(PsosTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        storageService.init();
    }
}

