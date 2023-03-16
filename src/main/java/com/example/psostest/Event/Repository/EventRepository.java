package com.example.psostest.Event.Repository;

import com.example.psostest.Event.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Integer> {
}
