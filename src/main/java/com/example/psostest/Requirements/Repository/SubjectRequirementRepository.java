package com.example.psostest.Requirements.Repository;

import com.example.psostest.Requirements.Entity.SubjectRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRequirementRepository extends JpaRepository<SubjectRequirement, Integer> {
}
