package com.nij.springcloud.controller;

import com.nij.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        String str = paymentService.paymentInfo_OK(id);
        log.info("****** result: " + str);
        return  str;
    }

    @GetMapping("/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id) {
        String str = paymentService.paymentInfo_Timeout(id);
        log.info("****** result: " + str);
        return  str;
    }

    // ---------------------
    // 服务熔断
    @GetMapping("/circuit/{id}")
    public String paymentInfo_circuit(@PathVariable("id") Integer id) {
        String str = paymentService.paymentCircuitBreaker(id);
        log.info("****** result: " + str);
        return  str;
    }
}
