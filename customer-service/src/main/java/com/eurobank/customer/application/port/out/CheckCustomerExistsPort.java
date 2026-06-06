package com.eurobank.customer.application.port.out;

public interface CheckCustomerExistsPort {
    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByEmail(String email);
}
