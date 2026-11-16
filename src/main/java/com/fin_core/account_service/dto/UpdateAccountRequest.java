package com.fin_core.account_service.dto;

import com.fin_core.account_service.entity.AccountStatus;
import com.fin_core.account_service.entity.AccountType;
import jakarta.validation.constraints.NotNull;

public record UpdateAccountRequest(

        @NotNull(message = "Account type is required")
        AccountType accountType,

        @NotNull(message = "Account status is required")
        AccountStatus status

) {}
