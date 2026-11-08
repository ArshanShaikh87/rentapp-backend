package com.arshan.rentapp_backend.auth.dto;

public record LoginResponse(Long id,
                            String loginId,
                            com.arshan.rentapp_backend.user.UserRole role) {
}
