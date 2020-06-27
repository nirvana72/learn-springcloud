package com.nij.springcloud.controller;

import com.nij.springcloud.entities.CommonResult;
import com.nij.springcloud.entities.Payment;
import com.nij.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feign")
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return  paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/test-timeout")
    public String paymentFeignTimeout() {
        // open-feign 客户端默认等待1秒
        return  paymentFeignService.paymentFeignTimeout();
    }
}
