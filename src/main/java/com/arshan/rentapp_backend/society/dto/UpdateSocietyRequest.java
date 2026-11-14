package com.arshan.rentapp_backend.society.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateSocietyRequest(
        @NotBlank(message = "Society name is required")
        String name,

        @NotBlank(message = "Address is required")
        String address,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "Pincode is required")
        String pincode
) {
}
