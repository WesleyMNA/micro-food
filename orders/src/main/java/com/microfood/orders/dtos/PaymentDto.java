package com.microfood.orders.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class PaymentDto {

    private Long id;
    @NotNull
    @Positive
    private BigDecimal value;
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String number;
    @NotBlank
    @NotNull
    private String expiration;
    @NotBlank
    @NotNull
    private String code;
    private StatusPayment status;
    @NotNull
    private Long orderId;
    @NotNull
    private Long paymentTypeId;
}
