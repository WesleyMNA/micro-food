package com.microfood.payments.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("orders")
public interface OrderClient {

    @PutMapping("/v1/orders/{id}/approve-payment")
    void approvePayment(@PathVariable Long id);
}
