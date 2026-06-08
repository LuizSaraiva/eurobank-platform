package com.eurobank.customer.application.exception;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String email) {
        super("Customer with email address '%s' already exists".formatted(email));
    }
}
