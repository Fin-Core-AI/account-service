package com.fin_core.account_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String mobileNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
