package com.eurobank.customer.application.usecase;

import com.eurobank.customer.application.CreateCustomerUseCaseImpl;
import com.eurobank.customer.application.port.in.CreateCustomerCommand;
import com.eurobank.customer.application.port.out.CheckCustomerExistsPort;
import com.eurobank.customer.application.port.out.SaveCustomerPort;
import com.eurobank.customer.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateCustomerUseCaseImplTest {

    @Mock
    private SaveCustomerPort saveCustomerPort;
    @Mock
    private CheckCustomerExistsPort checkCustomerExistsPort;

    private CreateCustomerUseCaseImpl createCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        createCustomerUseCase = new  CreateCustomerUseCaseImpl(
                saveCustomerPort,
                checkCustomerExistsPort
        );
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        CreateCustomerCommand command =
                new CreateCustomerCommand(
                        "Luiz",
                        "luiz@email.com",
                        "123456789"
                );

        when(checkCustomerExistsPort.existsByEmail(command.email()))
                .thenReturn(false);
        when(checkCustomerExistsPort.existsByDocumentNumber(command.documentNumber()))
                .thenReturn(false);

        when(saveCustomerPort.save(any(Customer.class)))
                .thenAnswer(invocation-> invocation.getArgument(0));

        Customer customer = createCustomerUseCase.execute(command);

        assertThat(customer).isNotNull();
        assertThat(customer.getEmail()).isEqualTo("luiz@email.com");
        assertThat(customer.getDocumentNumber()).isEqualTo("123456789");


        verify(checkCustomerExistsPort)
                .existsByEmail(command.email());

        verify(checkCustomerExistsPort)
                .existsByDocumentNumber(command.documentNumber());

        verify(saveCustomerPort)
                .save(any(Customer.class));

    }
}
