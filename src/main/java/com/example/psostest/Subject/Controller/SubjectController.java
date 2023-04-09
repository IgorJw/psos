package com.example.psostest.Subject.Controller;

import com.example.psostest.Config.Response.ResponseWithMessage;
import com.example.psostest.Config.Service.JwtService;
import com.example.psostest.Subject.Entity.Subject;
import com.example.psostest.Subject.Repository.SubjectRepository;
import com.example.psostest.Subject.Request.SubjectCreateRequest;
import com.example.psostest.Subject.Request.SubjectModifyRequest;
import com.example.psostest.Subject.Service.SubjectService;
import com.example.psostest.User.Entity.User;
import com.example.psostest.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class SubjectController {

    @Autowired
    SubjectService subjectService;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/subject/{subjectId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Map<String, String>> getSubjectInfo(@PathVariable Integer subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);

        Map<String, String> map = new HashMap<>();
        map.put("name", subject.getName());
        map.put("teacher", subject.getTeacher());

        return ResponseEntity.ok(map);
    }

    @PostMapping("/subject")
    public ResponseEntity<Subject> createSubject(@RequestBody SubjectCreateRequest request) {
        try {
            String userName = jwtService.extractUsername(request.getUserToken());
            User user = userRepository.findByUsername(userName).orElseThrow(NoSuchElementException::new);

            Subject newSubject = Subject
                    .builder()
                    .name(request.getName())
                    .teacher(request.getTeacher())
                    .user(user)
                    .build();
            subjectRepository.save(newSubject);
            return ResponseEntity.ok(newSubject);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/subject")
    public ResponseEntity<Subject> modifySubject(@RequestBody SubjectModifyRequest request) {
        try {
            Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(NoSuchElementException::new);
            if (request.getTeacher() != null)
                subject.setTeacher(request.getTeacher());
            if (request.getName() != null)
                subject.setName(request.getName());

            subjectRepository.save(subject);
            return ResponseEntity.ok(subject);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/subject/{subjectId}")
    public ResponseEntity<ResponseWithMessage> deleteSubject(@PathVariable Integer subjectId) {
        try {
            Subject subject = subjectRepository.findById(subjectId).orElseThrow(NoSuchElementException::new);
            subjectRepository.delete(subject);

            return ResponseEntity.ok(new ResponseWithMessage("Subject Deleted"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWithMessage("Subject to delete not found"));
        }
    }
}
