package com.fin_core.account_service.controller;

import com.fin_core.account_service.dto.CreateCustomerRequest;
import com.fin_core.account_service.dto.CustomerResponse;
import com.fin_core.account_service.dto.UpdateCustomerRequest;
import com.fin_core.account_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CreateCustomerRequest request) {

        CustomerResponse response = customerService.createCustomer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(
            @PathVariable UUID customerId) {

        return ResponseEntity.ok(
                customerService.getCustomer(customerId)
        );
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable UUID customerId,
            @Valid @RequestBody UpdateCustomerRequest request) {

        return ResponseEntity.ok(
                customerService.updateCustomer(customerId, request)
        );
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable UUID customerId) {

        customerService.deleteCustomer(customerId);

        return ResponseEntity.noContent().build();
    }
}

