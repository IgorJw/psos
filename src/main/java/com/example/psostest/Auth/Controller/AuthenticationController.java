package com.example.psostest.Auth.Controller;

import com.example.psostest.Auth.Request.LoginRequest;
import com.example.psostest.Auth.Request.RegisterRequest;
import com.example.psostest.Auth.Response.AuthenticationResponse;
import com.example.psostest.Auth.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authorize")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AuthenticationResponse> authorize(
            @RequestBody LoginRequest request
    ) throws CredentialNotFoundException {
        return ResponseEntity.ok(authenticationService.authorize(request));
    }
}
