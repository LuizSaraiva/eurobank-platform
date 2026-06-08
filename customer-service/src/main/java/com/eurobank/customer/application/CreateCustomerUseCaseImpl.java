package com.eurobank.customer.application;

import com.eurobank.customer.application.exception.DocumentNumberAlreadyExistsException;
import com.eurobank.customer.application.exception.EmailAlreadyExistsException;
import com.eurobank.customer.application.port.in.CreateCustomerCommand;
import com.eurobank.customer.application.port.in.CreateCustomerUseCase;
import com.eurobank.customer.application.port.out.CheckCustomerExistsPort;
import com.eurobank.customer.application.port.out.SaveCustomerPort;
import com.eurobank.customer.domain.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final SaveCustomerPort saveCustomerPort;
    private final CheckCustomerExistsPort checkCustomerExistsPort;

    public CreateCustomerUseCaseImpl(
            SaveCustomerPort saveCustomerPort,
            CheckCustomerExistsPort checkCustomerExistsPort
    ){
        this.saveCustomerPort = saveCustomerPort;
        this.checkCustomerExistsPort = checkCustomerExistsPort;
    }

    @Override
    public Customer execute(CreateCustomerCommand command) {

        if(checkCustomerExistsPort.existsByEmail(command.email())){
            throw new EmailAlreadyExistsException(command.email());
        }

        if(checkCustomerExistsPort.existsByDocumentNumber(command.documentNumber())){
            throw new DocumentNumberAlreadyExistsException(command.documentNumber());
        }

        Customer customer = Customer.create(
                command.fullName(),
                command.email(),
                command.documentNumber()
        );

        return saveCustomerPort.save(customer);
    }
}
