package com.teamgold.goldenharvestcustomsupport.customersupport.infrastructure.client.dto;

public record UserResponse(
        String email,
        String name,
        String company,
        String businessNumber,
        String phoneNumber,
        String addressLine1,
        String addressLine2,
        String postalCode,
        String status) {
}
