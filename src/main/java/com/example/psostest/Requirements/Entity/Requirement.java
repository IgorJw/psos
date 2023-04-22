package com.example.psostest.Requirements.Entity;

import com.example.psostest.Requirements.Enum.RequirementType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "subject_requirement_id")
    @JsonIgnore
    private SubjectRequirement subjectRequirement;

    private RequirementType requirementType;

    private Double requirementProgress;
}
