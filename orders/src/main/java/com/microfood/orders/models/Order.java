package com.microfood.orders.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "date_hour", nullable = false)
    private LocalDateTime dateHour;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
    private List<OrderItem> itens = new ArrayList<>();
}
