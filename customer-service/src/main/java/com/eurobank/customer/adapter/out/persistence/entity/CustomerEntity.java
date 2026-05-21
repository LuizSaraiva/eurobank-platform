package com.eurobank.customer.adapter.out.persistence.entity;

import com.eurobank.customer.domain.model.CustomerStatus;
import com.eurobank.customer.domain.model.KycStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "document_number")
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CustomerStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "kyc_status")
    private KycStatus kycStatus;

    @Column(name = "created_at")
    private Instant createdAt;

    protected CustomerEntity() {}


    public CustomerEntity(
            UUID id,
            String fullName,
            String email,
            String documentNumber,
            CustomerStatus customerStatus,
            KycStatus kycStatus,
            Instant createdAt
    ){
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.documentNumber = documentNumber;
        this.status = customerStatus;
        this.kycStatus = kycStatus;
        this.createdAt = createdAt;
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
