package com.arshan.rentapp_backend.property.dto;

import com.arshan.rentapp_backend.society.Society;

public record PropertyResponse(Long id,
                               Society societyId,
                               String propertyNumber,
                               String propertyType,
                               Integer floorNumber,
                               Boolean isActive) {
}
