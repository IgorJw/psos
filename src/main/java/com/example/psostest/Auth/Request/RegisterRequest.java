package com.example.psostest.Auth.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String name;
    private String surname;
    private Integer year;
    private String field_of_study;
    private String password;
}
