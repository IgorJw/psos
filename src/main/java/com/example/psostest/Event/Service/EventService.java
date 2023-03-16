package com.example.psostest.Event.Service;

import com.example.psostest.Event.Entity.Event;
import com.example.psostest.Event.Repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EventService {
    EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public Event getEventById(Integer eventId)
    {
        Event event = eventRepository.findById(eventId).orElseThrow(()->new NoSuchElementException());
        return event;
    }
}
