package com.eurobank.customer.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CustomerTest {

    @Test
    void shouldCreateCustomerWithDefaultStatus(){
        Customer customer = Customer.create(
                "Luiz Saraiva",
                "luiz@email.com",
                "123456"
        );

        assertThat(customer.getStatus()).isEqualTo(CustomerStatus.ACTIVE);
        assertThat(customer.getKycStatus()).isEqualTo(KycStatus.PENDING);
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldApproveKyc(){
        Customer customer = Customer.create(
                "Luiz Saraiva",
                "luiz@email.com",
                "123456"
        );

        customer.approveKyc();

        assertThat(customer.getKycStatus()).isEqualTo(KycStatus.APPROVED);
    }

    @Test
    void shouldRejectKyc(){
        Customer customer = Customer.create(
                "Luiz Saraiva",
                "luiz@email.com",
                "123456"
        );

        customer.rejectKyc();

        assertThat(customer.getKycStatus()).isEqualTo(KycStatus.REJECTED);
    }

    @Test
    void shouldBlockCustomer(){
        Customer customer = Customer.create(
                "Luiz Saraiva",
                "luiz@email.com",
                "123456"
        );

        customer.block();

        assertThat(customer.getStatus()).isEqualTo(CustomerStatus.BLOCKED);
    }

    @Test
    void shouldNotApproveKycWhenCustomerIsBlocked(){
        Customer customer = Customer.create(
                "Luiz Saraiva",
                "luiz@email.com",
                "123456"
        );

        customer.block();

        assertThatThrownBy(customer::approveKyc)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Blocked Customer cannot change KYC status");
    }
}
