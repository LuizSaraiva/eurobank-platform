package com.eurobank.customer.application.port.out;

import com.eurobank.customer.domain.model.Customer;

public interface SaveCustomerPort {
    Customer save(Customer customer);
}
