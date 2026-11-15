package com.arshan.rentapp_backend.property.dto;

import jakarta.validation.constraints.*;

public record UpdatePropertyRequest(
        @NotBlank(message = "Flat number is required")
        @Size(max = 20, message = "Flat number must be 20 characters or less")
        @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Only letters, numbers and '-' allowed")
        String propertyNumber,

        @NotBlank(message = "Flat type is required")
        @Size(max = 30, message = "Flat number must be 30 characters or less")
        String propertyType,

        Integer floorNumber) {
}
