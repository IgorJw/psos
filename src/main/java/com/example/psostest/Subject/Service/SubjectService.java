package com.example.psostest.Subject.Service;

import com.example.psostest.Subject.Entity.Subject;
import com.example.psostest.Subject.Repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SubjectService {
    SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject getSubjectById(Integer subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(()->new NoSuchElementException());
        return subject;
    }
}
