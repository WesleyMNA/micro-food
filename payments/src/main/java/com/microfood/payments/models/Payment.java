package com.microfood.payments.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "value", nullable = false)
    private BigDecimal value;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "expiration", nullable = false)
    private String expiration;
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "code", nullable = false)
    private String code;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @Column(name = "payment_type_id", nullable = false)
    private Long paymentTypeId;
}
