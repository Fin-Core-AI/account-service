package com.fin_core.account_service.service;

import com.fin_core.account_service.dto.CreateCustomerRequest;
import com.fin_core.account_service.dto.CustomerResponse;
import com.fin_core.account_service.dto.UpdateCustomerRequest;

import java.util.UUID;

public interface CustomerService {

    CustomerResponse createCustomer(CreateCustomerRequest request);

    CustomerResponse getCustomer(UUID customerId);

    CustomerResponse updateCustomer(UUID customerId, UpdateCustomerRequest request);

    void deleteCustomer(UUID customerId);
}

