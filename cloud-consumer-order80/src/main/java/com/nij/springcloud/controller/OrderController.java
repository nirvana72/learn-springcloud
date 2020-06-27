package com.nij.springcloud.controller;

import com.nij.springcloud.entities.CommonResult;
import com.nij.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {
    // public static final String BASE_URL = "http://localhost:8001";
    public static final String BASE_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(BASE_URL + "/payment/" + id, CommonResult.class);
    }

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(BASE_URL + "/payment/create", payment, CommonResult.class);
    }
}
