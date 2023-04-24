package com.example.psostest.Requirements.Request;

import com.example.psostest.Requirements.Enum.RequirementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequirementModifyRequest {
    Integer requirementId;
    String content;
    RequirementType requirementType;
    Double requirementProgress;

}
