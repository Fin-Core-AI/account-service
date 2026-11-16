package com.fin_core.account_service.dto;

import com.fin_core.account_service.entity.AccountStatus;
import com.fin_core.account_service.entity.AccountType;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponse(
        UUID id,
        String accountNumber,
        UUID customerId,
        AccountType accountType,
        AccountStatus status,
        String currency,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
