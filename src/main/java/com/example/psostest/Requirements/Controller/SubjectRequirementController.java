package com.example.psostest.Requirements.Controller;

import com.example.psostest.Requirements.Entity.Requirement;
import com.example.psostest.Requirements.Entity.SubjectRequirement;
import com.example.psostest.Requirements.Repository.RequirementRepository;
import com.example.psostest.Requirements.Repository.SubjectRequirementRepository;
import com.example.psostest.Requirements.Request.SubjectRequirementCreateRequest;
import com.example.psostest.Requirements.Request.SubjectRequirementDeleteRequest;
import com.example.psostest.Requirements.Request.SubjectRequirementModifyRequest;
import com.example.psostest.Shared.Response.ResponseWithMessage;
import com.example.psostest.Subject.Entity.Subject;
import com.example.psostest.Subject.Repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SubjectRequirementController {

    private final SubjectRequirementRepository subjectRequirementRepository;
    private final SubjectRepository subjectRepository;
    private final RequirementRepository requirementRepository;

    @GetMapping("/subject_requirement/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<SubjectRequirement> getAllSubjectRequirement(@PathVariable Integer id) {
        try {
            SubjectRequirement subjectRequirement = subjectRequirementRepository.findById(id).orElseThrow(NoSuchElementException::new);
            return ResponseEntity.ok(subjectRequirement);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/subject_requirement")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<SubjectRequirement> createSubjectRequirement(@RequestBody SubjectRequirementCreateRequest request) {
        try {
            Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(NoSuchElementException::new);
            SubjectRequirement subjectRequirement = SubjectRequirement
                    .builder()
                    .subject(subject)
                    .content(request.getContent())
                    .examType(request.getExamType())
                    .examCount(request.getExamCount())
                    .passCriteria(request.getPassCriteria())
                    .build();
            SubjectRequirement subjectRequirementSaved = subjectRequirementRepository.save(subjectRequirement);

            List<Requirement> requirements = request.getRequirements().stream()
                    .map(req -> Requirement.builder()
                            .content(req.getContent())
                            .subjectRequirement(subjectRequirementSaved)
                            .requirementType(req.getRequirementType())
                            .requirementProgress(req.getRequirementProgress())
                            .build())
                    .collect(Collectors.toList());

            List<Requirement> savedRequirements = requirementRepository.saveAll(requirements);

            subjectRequirementSaved.setRequirements(savedRequirements);

            return ResponseEntity.ok(subjectRequirement);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/subject_requirement")
    public ResponseEntity<ResponseWithMessage> modifySubjectRequirement(@RequestBody SubjectRequirementModifyRequest request) {
        try {
            SubjectRequirement subjectRequirement = subjectRequirementRepository.findById(request.getSubjectRequirementId()).orElseThrow(NoSuchElementException::new);

            if (request.getSubjectId() != null) {
                Subject subject = subjectRepository.findById(request.getSubjectId()).orElseThrow(NoSuchElementException::new);
                subjectRequirement.setSubject(subject);
            }

            if (request.getContent() != null) subjectRequirement.setContent(request.getContent());
            if (request.getExamType() != null) subjectRequirement.setExamType(request.getExamType());
            if (request.getExamCount() != null) subjectRequirement.setExamCount(request.getExamCount());
            if (request.getPassCriteria() != null) subjectRequirement.setPassCriteria(request.getPassCriteria());

            subjectRequirementRepository.save(subjectRequirement);

            return ResponseEntity.ok(new ResponseWithMessage("Subject requirement modified"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWithMessage("Subject requirement to modify not found"));
        }


    }

    @DeleteMapping("/subject_requirement")
    public ResponseEntity<ResponseWithMessage> deleteSubjectRequirement(@RequestBody SubjectRequirementDeleteRequest request) {
        try {
            SubjectRequirement subjectRequirement = subjectRequirementRepository.findById(request.getSubjectRequirementId()).orElseThrow(NoSuchElementException::new);
            subjectRequirementRepository.delete(subjectRequirement);
            return ResponseEntity.ok(new ResponseWithMessage("Subject requirement deleted"));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWithMessage("Subject requirement not found"));
        }
    }

}
