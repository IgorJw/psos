package com.example.psostest.Subject.Controller;

import com.example.psostest.Shared.Response.ResponseWithMessage;
import com.example.psostest.Subject.Entity.Subject;
import com.example.psostest.Subject.Repository.SubjectRepository;
import com.example.psostest.Subject.Request.SubjectCreateRequest;
import com.example.psostest.Subject.Request.SubjectModifyRequest;
import com.example.psostest.Subject.Service.SubjectService;
import com.example.psostest.User.Entity.User;
import com.example.psostest.User.Service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectRepository subjectRepository;
    private final UsersService usersService;

    @GetMapping("/subject/{subjectId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Subject> getSubjectInfo(@PathVariable Integer subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(subject);
    }

    @PostMapping("/subject")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Subject> createSubject(HttpServletRequest token, @RequestBody SubjectCreateRequest request) {
        try {
            User user = usersService.getLoggedUser(token);
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
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ResponseWithMessage> modifySubject(@RequestBody SubjectModifyRequest request) {
        try {
            Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(NoSuchElementException::new);
            if (request.getTeacher() != null)
                subject.setTeacher(request.getTeacher());
            if (request.getName() != null)
                subject.setName(request.getName());

            subjectRepository.save(subject);
            return ResponseEntity.ok(new ResponseWithMessage("Subject modified"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWithMessage("Subject to modify not found"));
        }
    }

    @DeleteMapping("/subject/{subjectId}")
    @CrossOrigin(origins = "http://localhost:3000")
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
