package com.arshan.rentapp_backend.society.dto;

public record CreateSocietyResponse(
        Long id,
        String name,
        String address,
        String city,
        String state,
        String pincode,
        Boolean isActive
) {
}
