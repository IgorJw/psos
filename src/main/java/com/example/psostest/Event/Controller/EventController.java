package com.example.psostest.Event.Controller;

import com.example.psostest.Event.Entity.Event;
import com.example.psostest.Event.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

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
}
