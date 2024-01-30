package com.microfood.payments.dtos;

import com.microfood.payments.models.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDto {

    private Long id;
    @NotNull
    @Positive
    private BigDecimal value;
    @NotBlank
    private String name;
    @NotBlank
    private String expiration;
    @NotBlank
    private String code;
    private Status status;
    @NotNull
    private Long orderId;
    @NotNull
    private Long paymentTypeIdd;
}
