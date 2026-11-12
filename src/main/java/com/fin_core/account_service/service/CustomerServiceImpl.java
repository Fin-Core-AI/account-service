package com.fin_core.account_service.service;

import com.fin_core.account_service.dto.CreateCustomerRequest;
import com.fin_core.account_service.dto.CustomerResponse;
import com.fin_core.account_service.dto.UpdateCustomerRequest;

import java.util.UUID;

import com.fin_core.account_service.entity.Customer;
import com.fin_core.account_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        if (customerRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already registered");
        }

        if (customerRepository.existsByMobileNumber(request.mobileNumber())) {
            throw new IllegalArgumentException("Mobile number already registered");
        }

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .mobileNumber(request.mobileNumber())
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return mapToResponse(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getCustomer(UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(UUID customerId, UpdateCustomerRequest request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));

        if (customerRepository.existsByEmail(request.email())
                && !customer.getEmail().equalsIgnoreCase(request.email())) {
            throw new IllegalArgumentException("Email already registered");
        }

        if (customerRepository.existsByMobileNumber(request.mobileNumber())
                && !customer.getMobileNumber().equals(request.mobileNumber())) {
            throw new IllegalArgumentException("Mobile number already registered");
        }

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setMobileNumber(request.mobileNumber());

        Customer updatedCustomer = customerRepository.save(customer);

        return mapToResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(UUID customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        customerRepository.delete(customer);
    }

    private CustomerResponse mapToResponse(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}
