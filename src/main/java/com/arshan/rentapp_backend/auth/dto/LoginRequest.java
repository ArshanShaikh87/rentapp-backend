package com.arshan.rentapp_backend.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "Login ID is required")  String loginId,
        @NotBlank(message = "Password is required")  String password) {

}
