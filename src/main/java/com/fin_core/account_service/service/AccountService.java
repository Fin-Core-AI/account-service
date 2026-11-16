package com.fin_core.account_service.service;

import com.fin_core.account_service.dto.AccountResponse;
import com.fin_core.account_service.dto.CreateAccountRequest;
import com.fin_core.account_service.dto.UpdateAccountRequest;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest request);

    AccountResponse getAccount(UUID accountId);

    AccountResponse updateAccount(UUID accountId, UpdateAccountRequest request);

    List<AccountResponse> getAccountsByCustomer(UUID customerId);
}
