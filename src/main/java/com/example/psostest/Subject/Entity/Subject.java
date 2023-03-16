package com.example.psostest.Subject.Entity;

import com.example.psostest.Event.Entity.Event;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue()
    private Integer id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name = "teacher",nullable = false)
    private String teacher;
    @OneToMany(mappedBy = "subject")
    private Set<Event> events;

    public Subject(){}

    public Subject(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
