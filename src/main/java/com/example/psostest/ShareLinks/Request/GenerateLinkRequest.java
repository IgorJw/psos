package com.example.psostest.ShareLinks.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateLinkRequest {
    private Set<Integer> fileIds;
}
