package com.example.psostest.Requirements.Repository;

import com.example.psostest.Requirements.Entity.Requirement;
import com.example.psostest.Requirements.Entity.SubjectRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Integer> {
    List<Requirement> findAllBySubjectRequirement(SubjectRequirement subjectRequirement);
}
