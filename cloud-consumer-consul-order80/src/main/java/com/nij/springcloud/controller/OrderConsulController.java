package com.nij.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderConsulController {
    // public static final String BASE_URL = "http://localhost:8001";
    public static final String BASE_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        return restTemplate.getForObject(BASE_URL + "/payment/test", String.class);
    }
}
