package com.eurobank.customer.application.port.out;

import com.eurobank.customer.adapter.out.persistence.entity.CustomerEntity;
import com.eurobank.customer.adapter.out.persistence.mapper.CustomerMapper;
import com.eurobank.customer.adapter.out.persistence.repository.CustomerJpaRepository;
import com.eurobank.customer.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerPersistenceAdapter implements
        SaveCustomerPort,
        FindCustomerByDocumentPort,
        CheckCustomerExistsPort {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerPersistenceAdapter(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }


    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return customerJpaRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public Optional<Customer> findByDocumentNumber(String documentNumber) {
        return customerJpaRepository
                .findByDocumentNumber(documentNumber)
                .map(CustomerMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }

    @Override
    public Customer save(Customer customer) {

        CustomerEntity entity = CustomerMapper.toEntity(customer);

        return CustomerMapper.toDomain(customerJpaRepository.save(entity));
    }
}
