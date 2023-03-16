package com.example.psostest.Event.Entity;

import com.example.psostest.Event.Enum.EventPriority;
import com.example.psostest.Subject.Entity.Subject;
import com.example.psostest.User.Entity.Users;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private Users user;
    @ManyToOne
    @JoinColumn(name="subject_id",nullable = false)
    private Subject subject;
    @Column(name="content")
    private String content;
    @Column(name="date")
    private Date date;
    @Column(name="event_priority")
    private EventPriority priority;

    public Event(){}

    public Event(Users user, Subject subject, String content, Date date, EventPriority priority) {
        this.user = user;
        this.subject = subject;
        this.content = content;
        this.date = date;
        this.priority = priority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public void setPriority(EventPriority priority) {
        this.priority = priority;
    }
}
