package com.example.psostest.Event.Controller;

import com.example.psostest.Event.Entity.Event;
import com.example.psostest.Event.Repository.EventRepository;
import com.example.psostest.Event.Request.EventCreateRequest;
import com.example.psostest.Event.Request.EventModifyRequest;
import com.example.psostest.Shared.Response.ResponseWithMessage;
import com.example.psostest.Subject.Entity.Subject;
import com.example.psostest.Subject.Repository.SubjectRepository;
import com.example.psostest.User.Entity.User;
import com.example.psostest.User.Service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;
    private final SubjectRepository subjectRepository;
    private final UsersService usersService;

    @GetMapping("/event/{eventId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Map<String, String>> getEventInfo(@PathVariable Integer eventId) {
        try {
            Event event = eventRepository.findById(eventId).orElseThrow(NoSuchElementException::new);

            Map<String, String> map = new HashMap<>();
            map.put("content", event.getContent());
            map.put("subject", event.getSubject().getName());
            map.put("date", event.getDate().toString());
            map.put("priority", event.getPriority().toString());

            return ResponseEntity.ok(map);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/events/{date}")
    public ResponseEntity<List<Event>> getEventsByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate date) {
        List<Event> events = eventRepository.findAllByDate(date);
        return ResponseEntity.ok(events);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(HttpServletRequest token, @RequestBody EventCreateRequest request) {
        User user = usersService.getLoggedUser(token);
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
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ResponseWithMessage> modifyEvent(@RequestBody EventModifyRequest request) {
        try {
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
            return ResponseEntity.ok(new ResponseWithMessage("Event modified"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWithMessage("Event to modify not found"));
        }
    }

    @DeleteMapping("/event/{eventId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ResponseWithMessage> deleteEvent(@PathVariable Integer eventId) {
        try {
            Subject subject = subjectRepository.findById(eventId).orElseThrow(NoSuchElementException::new);
            subjectRepository.delete(subject);

            return ResponseEntity.ok(new ResponseWithMessage("Event Deleted"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWithMessage("Event to delete not found"));
        }
    }
}
