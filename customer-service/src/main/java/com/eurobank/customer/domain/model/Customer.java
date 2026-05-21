package com.eurobank.customer.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Customer {

    private final UUID id;
    private final String fullName;
    private final String email;
    private final String documentNumber;
    private CustomerStatus status;
    private KycStatus kycStatus;
    private final Instant createdAt;

    private Customer(
            UUID id,
            String fullName,
            String email,
            String documentNumber,
            CustomerStatus status,
            KycStatus kycStatus,
            Instant createdAt) {

        this.id = Objects.requireNonNull(id, "Customer id is required");
        this.fullName = validateRequired(fullName, "Full name is required");
        this.email = validateRequired(email, "Email is required");
        this.documentNumber = validateRequired(documentNumber, "Document number is required");
        this.status = Objects.requireNonNull(status, "Customer status is required");
        this.kycStatus = Objects.requireNonNull(kycStatus, "Kyc status is required");
        this.createdAt = Objects.requireNonNull(createdAt, "Created at is required");
    }

    public static Customer create(
            String fullName,
            String email,
            String documentNumber) {
    return new  Customer(
            UUID.randomUUID(),
            fullName,
            email,
            documentNumber,
            CustomerStatus.ACTIVE,
            KycStatus.PENDING,
            Instant.now());
    }

    public void approveKyc() {
        ensureCustomerIsNotBlocked();
        this.kycStatus = KycStatus.APPROVED;
    }

    public void rejectKyc() {
        ensureCustomerIsNotBlocked();
        this.kycStatus = KycStatus.REJECTED;
    }

    public void block(){
        this.status = CustomerStatus.BLOCKED;
    }

    public void deactivate(){
        this.status = CustomerStatus.INACTIVE;
    }

    private void ensureCustomerIsNotBlocked() {
        if (this.status == CustomerStatus.BLOCKED) {
            throw new IllegalStateException("Blocked Customer cannot change KYC status");
        }
    }

    private String validateRequired(String value, String message) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException(message);
            }
            return value.trim();
        }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public KycStatus getKycStatus() {
        return kycStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
