package com.example.psostest.Requirements.Controller;

import com.example.psostest.Requirements.Entity.Requirement;
import com.example.psostest.Requirements.Repository.RequirementRepository;
import com.example.psostest.Requirements.Request.RequirementModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class RequirementController {

    private final RequirementRepository requirementRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/requirement/")
    public ResponseEntity<Requirement> modifyRequirement(@RequestBody RequirementModifyRequest request) {
        try{
            Requirement requirement = requirementRepository.findById(request.getRequirementId()).orElseThrow(NoSuchElementException::new);
            if (request.getContent() != null) requirement.setContent(request.getContent());
            if (request.getRequirementType() != null) requirement.setRequirementType(request.getRequirementType());
            if (request.getRequirementProgress() != null)
                requirement.setRequirementProgress(request.getRequirementProgress());
            requirementRepository.save(requirement);
            return ResponseEntity.ok(requirement);
        } catch (NoSuchElementException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

}
