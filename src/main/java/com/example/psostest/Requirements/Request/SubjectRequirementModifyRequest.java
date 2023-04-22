package com.example.psostest.Requirements.Request;

import com.example.psostest.Requirements.Entity.Requirement;
import com.example.psostest.Requirements.Enum.ExamType;
import com.example.psostest.Requirements.Enum.PassCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequirementModifyRequest {
    Integer subjectRequirementId;
    Integer subjectId;
    String content;
    ExamType examType;
    List<Requirement> requirements;
    Integer examCount;
    PassCriteria passCriteria;

}
