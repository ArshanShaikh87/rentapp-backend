package com.arshan.rentapp_backend.society.dto;

import jakarta.validation.constraints.NotBlank;

public record SocietyResponse(
        Long id,
        String name,
        String address,
        String city,
        String state,
        String pincode,
        Boolean isActive
) {
}
