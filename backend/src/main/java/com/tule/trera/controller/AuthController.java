package com.tule.trera.controller;

import com.tule.trera.dto.request.LoginRequest;
import com.tule.trera.dto.request.RegisterRequest;
import com.tule.trera.dto.response.ApiResponse;
import com.tule.trera.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(
            @RequestBody LoginRequest request
    ) {
        String accessToken = authService.login(request.getEmail(), request.getPassword());

        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Successfully logged in")
                .data(accessToken)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(
            @RequestBody RegisterRequest request
    ) {
        String accessToken = authService.register(request.getEmail(), request.getPassword());

        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Successfully registered")
                .data(accessToken)
                .build();

        return ResponseEntity.ok(response);
    }
}
