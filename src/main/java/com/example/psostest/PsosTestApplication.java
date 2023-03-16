package com.example.psostest;

import com.example.psostest.Event.Entity.Event;
import com.example.psostest.Event.Enum.EventPriority;
import com.example.psostest.Event.Repository.EventRepository;
import com.example.psostest.Event.Service.EventService;
import com.example.psostest.Subject.Entity.Subject;
import com.example.psostest.Subject.Repository.SubjectRepository;
import com.example.psostest.Subject.Service.SubjectService;
import com.example.psostest.User.Entity.Users;
import com.example.psostest.User.Entity.UsersBasicInfo;
import com.example.psostest.User.Repository.UsersBasicInfoRepository;
import com.example.psostest.User.Repository.UsersRepository;
import com.example.psostest.User.Service.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class PsosTestApplication implements CommandLineRunner {

	EventService eventService;
	UsersService usersService;
	SubjectService subjectService;
	UsersRepository userRepository;
	UsersBasicInfoRepository usersBasicInfoRepository;
	SubjectRepository subjectRepository;
	EventRepository eventRepository;
	public PsosTestApplication(UsersRepository userRepository, UsersBasicInfoRepository usersBasicInfo, UsersService usersService, SubjectRepository subjectRepository, EventRepository eventRepository, SubjectService subjectService, EventService eventService) {
		this.userRepository = userRepository;
		this.usersBasicInfoRepository = usersBasicInfo;
		this.usersService = usersService;
		this.subjectRepository = subjectRepository;
		this.eventRepository = eventRepository;
		this.subjectService = subjectService;
		this.eventService = eventService;
	}

	public static void main(String[] args) {
		SpringApplication.run(PsosTestApplication.class, args);
	}

	@Override
	public void run(String... args)
	{
		Users user = new Users("login","haslo");
		userRepository.save(user);

		UsersBasicInfo userInfo = new UsersBasicInfo("Kuba","Maliszewski",2,user);
		usersBasicInfoRepository.save(userInfo);

		Subject subject = new Subject("Analiza I","Wojciech Bulatek");
		subjectRepository.save(subject);

		Event event = new Event(user,subject,"Przykladowy event",new Date(), EventPriority.BIG);
		eventRepository.save(event);
	}

	@GetMapping("/user")
	@CrossOrigin(origins = "http://localhost:3000")
	public Map<String,String> getUserInfo(@RequestParam Integer userId) {
		HashMap<String, String> map = new HashMap<>();
		UsersBasicInfo userInfo = usersService.getUserBasicInfoByUserId(userId);

		map.put("name", userInfo.getName().toString());
		map.put("surname", userInfo.getSurname().toString());
		map.put("year", userInfo.getYear().toString());

		return map;
	}
	@GetMapping("/user/{userId}/event/")
	@CrossOrigin(origins = "http://localhost:3000")
	public Map<String,String> getEventInfo (@RequestParam Integer eventId, @PathVariable String userId)
	{
		HashMap<String,String> map = new HashMap<>();
		Event event = eventService.getEventById(eventId);
		map.put("content",event.getContent().toString());
		map.put("subject",event.getSubject().getName().toString());
		map.put("date",event.getDate().toString());
		map.put("priority",event.getPriority().toString());

		return map;
	}
	@GetMapping("/subject")
	@CrossOrigin(origins = "http://localhost:3000")
	public Map<String,String> getSubjectInfo (@RequestParam Integer subjectId)
	{
		HashMap<String,String> map = new HashMap<>();
		Subject subject = subjectService.getSubjectById(subjectId);
		map.put("name",subject.getName().toString());
		map.put("teacher",subject.getTeacher().toString());
		return map;
	}


}
