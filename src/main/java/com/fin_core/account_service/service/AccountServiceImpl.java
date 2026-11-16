package com.fin_core.account_service.service;

import com.fin_core.account_service.dto.AccountResponse;
import com.fin_core.account_service.dto.CreateAccountRequest;
import com.fin_core.account_service.dto.UpdateAccountRequest;
import com.fin_core.account_service.entity.Account;
import com.fin_core.account_service.entity.AccountStatus;
import com.fin_core.account_service.entity.Customer;
import com.fin_core.account_service.repository.AccountRepository;
import com.fin_core.account_service.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {

        UUID customerId = UUID.fromString(request.customerId());

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Customer not found: " + customerId
                        ));

        Account account = Account.builder()
                .accountNumber(generateAccountNumber())
                .customer(customer)
                .accountType(request.accountType())
                .status(AccountStatus.ACTIVE)
                .currency(request.currency() == null
                        ? "INR"
                        : request.currency())
                .build();

        Account savedAccount = accountRepository.save(account);

        return mapToResponse(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponse getAccount(UUID accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Account not found: " + accountId
                        ));

        return mapToResponse(account);
    }

    @Override
    public AccountResponse updateAccount(
            UUID accountId,
            UpdateAccountRequest request) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Account not found: " + accountId
                        ));

        if (account.getStatus() == AccountStatus.CLOSED) {
            throw new IllegalStateException(
                    "Closed accounts cannot be updated"
            );
        }

        account.setAccountType(request.accountType());
        account.setStatus(request.status());

        Account updatedAccount = accountRepository.save(account);

        return mapToResponse(updatedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> getAccountsByCustomer(UUID customerId) {

        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private String generateAccountNumber() {

        return "FC" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 14)
                .toUpperCase();
    }

    private AccountResponse mapToResponse(Account account) {

        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getCustomer().getId(),
                account.getAccountType(),
                account.getStatus(),
                account.getCurrency(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }
}
