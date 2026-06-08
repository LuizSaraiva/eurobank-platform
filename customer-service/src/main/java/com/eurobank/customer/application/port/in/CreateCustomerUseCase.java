package com.eurobank.customer.application.port.in;

import com.eurobank.customer.domain.model.Customer;

public interface CreateCustomerUseCase {
    Customer execute(CreateCustomerCommand command);
}
