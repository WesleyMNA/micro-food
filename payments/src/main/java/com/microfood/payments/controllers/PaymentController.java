package com.microfood.payments.controllers;

import com.microfood.payments.dtos.PaymentDto;
import com.microfood.payments.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    private final PaymentService service;

    @GetMapping
    public ResponseEntity<Page<PaymentDto>> findAll(@ParameterObject Pageable pageable) {
        Page<PaymentDto> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody @Valid PaymentDto dto,
                                             UriComponentsBuilder builder) {
        PaymentDto response = service.create(dto);
        URI uri = builder.path("/v1/payments/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity
                .created(uri)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid PaymentDto dto) {
        service.update(id, dto);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<Void> approvePayment(@PathVariable Long id) {
        service.approvePayment(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
