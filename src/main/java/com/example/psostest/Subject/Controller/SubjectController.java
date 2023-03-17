package com.example.psostest.Subject.Controller;

import com.example.psostest.Subject.Entity.Subject;
import com.example.psostest.Subject.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/subject/{subjectId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, String> getSubjectInfo(@PathVariable Integer subjectId) {

        Subject subject = subjectService.getSubjectById(subjectId);

        HashMap<String, String> map = new HashMap<>();
        map.put("name", subject.getName());
        map.put("teacher", subject.getTeacher());

        return map;
    }
}
