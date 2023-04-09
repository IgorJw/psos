package com.example.psostest.Event.Controller;

import com.example.psostest.Config.Service.JwtService;
import com.example.psostest.Event.Entity.Event;
import com.example.psostest.Event.Repository.EventRepository;
import com.example.psostest.Event.Request.EventCreateRequest;
import com.example.psostest.Event.Request.EventModifyRequest;
import com.example.psostest.Subject.Entity.Subject;
import com.example.psostest.Subject.Repository.SubjectRepository;
import com.example.psostest.User.Entity.User;
import com.example.psostest.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    JwtService jwtService;

    @GetMapping("/event/{eventId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Map<String, String>> getEventInfo(@PathVariable Integer eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(NoSuchElementException::new);

        Map<String, String> map = new HashMap<>();
        map.put("content", event.getContent());
        map.put("subject", event.getSubject().getName());
        map.put("date", event.getDate().toString());
        map.put("priority", event.getPriority().toString());

        return ResponseEntity.ok(map);
    }

    @GetMapping("/events/{date}")
    public ResponseEntity<List<Event>> getEventsByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate date) {
        List<Event> events = eventRepository.findAllByDate(date);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestBody EventCreateRequest request) {
        String userName = jwtService.extractUsername(request.getUserToken());
        User user = userRepository.findByUsername(userName).orElseThrow(NoSuchElementException::new);
        Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(NoSuchElementException::new);
        Event newEvent = Event
                .builder()
                .user(user)
                .subject(subject)
                .content(request.getContent())
                .date(request.getDate())
                .priority(request.getPriority())
                .build();
        eventRepository.save(newEvent);
        return ResponseEntity.ok(newEvent);
    }

    @PutMapping("/event")
    public ResponseEntity<Event> modifyEvent(@RequestBody EventModifyRequest request) {
        Event event = eventRepository.findById(request.getEventId()).orElseThrow(NoSuchElementException::new);

        if (request.getContent() != null) {
            event.setContent(request.getContent());
        }

        if (request.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(NoSuchElementException::new);
            event.setSubject(subject);
        }

        if (request.getDate() != null)
            event.setDate(request.getDate());

        if (request.getPriority() != null)
            event.setPriority(request.getPriority());

        eventRepository.save(event);
        return ResponseEntity.ok(event);
    }
}
