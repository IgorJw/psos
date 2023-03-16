package com.example.psostest.Subject.Repository;

import com.example.psostest.Subject.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
