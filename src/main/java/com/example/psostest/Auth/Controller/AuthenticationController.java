package com.example.psostest.Auth.Controller;

import com.example.psostest.Auth.Request.LoginRequest;
import com.example.psostest.Auth.Request.RegisterRequest;
import com.example.psostest.Auth.Response.AuthenticationResponse;
import com.example.psostest.Auth.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InstanceAlreadyExistsException;
import javax.security.auth.login.CredentialNotFoundException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws InstanceAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authorize")
    public ResponseEntity<AuthenticationResponse> authorize(
            @RequestBody LoginRequest request
    ) throws CredentialNotFoundException {
        return ResponseEntity.ok(authenticationService.authorize(request));
    }
}
