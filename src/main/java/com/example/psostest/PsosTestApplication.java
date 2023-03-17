package com.example.psostest;

import com.example.psostest.Event.Entity.Event;
import com.example.psostest.Event.Enum.EventPriority;
import com.example.psostest.Event.Repository.EventRepository;
import com.example.psostest.Subject.Entity.Subject;
import com.example.psostest.Subject.Repository.SubjectRepository;
import com.example.psostest.Subject.Service.SubjectService;
import com.example.psostest.User.Entity.Users;
import com.example.psostest.User.Entity.UsersBasicInfo;
import com.example.psostest.User.Repository.UsersBasicInfoRepository;
import com.example.psostest.User.Repository.UsersRepository;
import com.example.psostest.User.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class PsosTestApplication implements CommandLineRunner {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UsersBasicInfoRepository usersBasicInfoRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    EventRepository eventRepository;

    @Autowired
    public PsosTestApplication(UsersRepository usersRepository, UsersBasicInfoRepository usersBasicInfo, UsersService usersService, SubjectRepository subjectRepository, EventRepository eventRepository, SubjectService subjectService) {
        this.usersRepository = usersRepository;
        this.usersBasicInfoRepository = usersBasicInfo;
        this.subjectRepository = subjectRepository;
        this.eventRepository = eventRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PsosTestApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Users user = new Users("login", "haslo");
        usersRepository.save(user);

        UsersBasicInfo userInfo = new UsersBasicInfo(
                "Kuba",
                "Maliszewski",
                2,
                user
        );
        usersBasicInfoRepository.save(userInfo);

        Subject subject = new Subject("Analiza I", "Wojciech Bulatek");
        subjectRepository.save(subject);

        LocalDate today = LocalDate.now();
        Event event = new Event(
                user,
                subject,
                "Przykladowy event",
                today,
                EventPriority.BIG
        );
        eventRepository.save(event);
    }
}

