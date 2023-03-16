package com.example.psostest.User.Entity;

import com.example.psostest.Event.Entity.Event;
import jakarta.persistence.*;

import java.util.Set;
@Entity
public class Users {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "login",nullable = false)
    private String login;
    @Column(name = "password",nullable = false)
    private String password;
    @OneToOne(mappedBy = "user")
    private UsersBasicInfo usersBasicInfo;
    @OneToMany(mappedBy = "user")
    private Set<Event> events;

    public Users() {}

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsersBasicInfo getUsersBasicInfo() {
        return usersBasicInfo;
    }

    public void setUsersBasicInfo(UsersBasicInfo usersBasicInfo) {
        this.usersBasicInfo = usersBasicInfo;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
