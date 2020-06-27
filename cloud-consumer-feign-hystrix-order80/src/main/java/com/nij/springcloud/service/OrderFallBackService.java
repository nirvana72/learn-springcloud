package com.nij.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderFallBackService implements OrderHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        String str = "OrderFallBackService at order80 ";
        return str;
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        String str = "OrderFallBackService at order80 ";
        return str;
    }
}
