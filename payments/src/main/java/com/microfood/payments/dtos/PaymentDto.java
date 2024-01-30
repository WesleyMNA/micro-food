package com.microfood.payments.dtos;

import com.microfood.payments.models.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDto {

    private Long id;
    private BigDecimal value;
    private String name;
    private String expiration;
    private String code;
    private Status status;
    private Long orderId;
    private Long paymentTypeIdd;
}
