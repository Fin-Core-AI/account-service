
package com.fin_core.account_service.controller;

import com.fin_core.account_service.dto.AccountResponse;
import com.fin_core.account_service.dto.CreateAccountRequest;
import com.fin_core.account_service.dto.UpdateAccountRequest;
import com.fin_core.account_service.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount
            (@Valid @RequestBody CreateAccountRequest request) {

        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable UUID accountId) {
        return ResponseEntity.ok(accountService.getAccount(accountId));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountResponse> updateAccount(
            @PathVariable UUID accountId,
            @Valid @RequestBody UpdateAccountRequest request) {

        return ResponseEntity.ok(accountService.updateAccount(accountId, request));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountResponse>> getAccountsByCustomer(
            @PathVariable UUID customerId) {

        return ResponseEntity.ok(
                accountService.getAccountsByCustomer(customerId)
        );
    }
}
