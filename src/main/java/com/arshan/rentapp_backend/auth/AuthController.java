package com.arshan.rentapp_backend.auth;

import com.arshan.rentapp_backend.auth.dto.LoginRequest;
import com.arshan.rentapp_backend.auth.dto.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response){
        return authService.login(loginRequest, request, response);
    }

    @GetMapping("/me")
    public LoginResponse getCurrentUser() {
        return authService.getCurrentUser();
    }

    @GetMapping("/csrf")
    public CsrfToken csrfToken(CsrfToken csrfToken){
        return csrfToken;
    }
}
