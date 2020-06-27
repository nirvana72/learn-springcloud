package com.nij.springcloud.controller;

import com.nij.springcloud.entities.CommonResult;
import com.nij.springcloud.entities.Payment;
import com.nij.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private HttpServletRequest request;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***** 插入结果 ：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据成功,port:" + this.serverPort, result);
        } else {
            return new CommonResult(500, "插入数据失败", null);
        }
    }

    @GetMapping("/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***** 查询结果 ：" + payment);
        String token = this.request.getHeader("token");
        log.info("***** token ：" + token);

        if (payment != null) {
            return new CommonResult(200, "查询成功,port:" + this.serverPort, payment);
        } else {
            return new CommonResult(500, "查询失败,ID = " + id, null);
        }
    }
}
