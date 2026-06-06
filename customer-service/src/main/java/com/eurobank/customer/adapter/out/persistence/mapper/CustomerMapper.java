package com.eurobank.customer.adapter.out.persistence.mapper;

import com.eurobank.customer.adapter.out.persistence.entity.CustomerEntity;
import com.eurobank.customer.domain.model.Customer;

public final class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerEntity toEntity(Customer customer) {
        return new CustomerEntity(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getDocumentNumber(),
                customer.getStatus(),
                customer.getKycStatus(),
                customer.getCreatedAt()
        );
    }

    public static Customer toDomain(CustomerEntity customerEntity) {
        return Customer.restore(
                customerEntity.getId(),
                customerEntity.getFullName(),
                customerEntity.getEmail(),
                customerEntity.getDocumentNumber(),
                customerEntity.getStatus(),
                customerEntity.getKycStatus(),
                customerEntity.getCreatedAt()
        );
    }
}
