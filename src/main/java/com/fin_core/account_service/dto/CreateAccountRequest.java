package com.fin_core.account_service.dto;


import com.fin_core.account_service.entity.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateAccountRequest(

        @NotNull(message = "Customer ID is required")
        String customerId,

        @NotNull(message = "Account type is required")
        AccountType accountType,

        @Pattern(
                regexp = "^[A-Z]{3}$",
                message = "Currency must be a valid 3-letter uppercase code"
        )
        @Size(min = 3, max = 3)
        String currency

) {}

