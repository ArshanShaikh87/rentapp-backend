package com.arshan.rentapp_backend.society.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateSocietyRequest(
        @NotBlank(message = "Society name is required")
        @Size(min = 3, max = 100, message = "Society name must be between 3 and 100 characters")
        String name,

        @NotBlank(message = "Address is required")
        @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
        String address,

        @NotBlank(message = "City is required")
        @Size(min = 2, max = 50, message = "City name must be between 2 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City name must contain only letters and spaces")
        String city,

        @NotBlank(message = "State is required")
        @Size(min = 2, max = 50, message = "State name must be between 2 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "State name must contain only letters and spaces")
        String state,

        @NotBlank(message = "Pincode is required")
        @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Pincode must be a valid 6-digit number")
        String pincode
) {
}
