package com.eurobank.customer.application.port.in;

public record CreateCustomerCommand (
        String fullName,
        String email,
        String documentNumber
){}
