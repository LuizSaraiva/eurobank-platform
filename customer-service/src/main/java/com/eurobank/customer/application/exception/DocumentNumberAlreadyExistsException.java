package com.eurobank.customer.application.exception;

public class DocumentNumberAlreadyExistsException extends RuntimeException {
    public DocumentNumberAlreadyExistsException(String message) {
        super("Customer with documentNumber '%s' already exists".formatted(message));
    }
}
