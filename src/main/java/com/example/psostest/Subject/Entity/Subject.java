package com.example.psostest.Subject.Entity;

import com.example.psostest.Event.Entity.Event;
import com.example.psostest.Requirements.Entity.SubjectRequirement;
import com.example.psostest.User.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "teacher", nullable = false)
    private String teacher;
    @OneToMany(mappedBy = "subject", cascade = {CascadeType.REMOVE})
    private List<Event> events;
    @OneToMany(mappedBy = "subject", cascade = {CascadeType.REMOVE})
    private List<SubjectRequirement> subjectRequirements;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
