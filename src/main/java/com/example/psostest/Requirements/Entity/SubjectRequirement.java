package com.example.psostest.Requirements.Entity;

import com.example.psostest.Requirements.Enum.ExamType;
import com.example.psostest.Requirements.Enum.PassCriteria;
import com.example.psostest.Subject.Entity.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnore
    private Subject subject;
    private String content;

    @Enumerated(EnumType.STRING)
    private ExamType examType;

    private Integer examCount;

    @Enumerated(EnumType.STRING)
    private PassCriteria passCriteria;

    @OneToMany(mappedBy = "subjectRequirement", cascade = CascadeType.ALL)
    private List<Requirement> requirements;
}
