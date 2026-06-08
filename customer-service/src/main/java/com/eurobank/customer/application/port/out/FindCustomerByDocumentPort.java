package com.eurobank.customer.application.port.out;

import com.eurobank.customer.domain.model.Customer;

import java.util.Optional;

public interface FindCustomerByDocumentPort {
    Optional<Customer> findByDocumentNumber(String documentNumber);
    boolean existsByEmail(String email);
}
