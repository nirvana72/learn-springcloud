package com.nij.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nij.springcloud.service.OrderHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hystrix")
@Slf4j
@DefaultProperties(defaultFallback = "global_fallback") // 全局服务降级
public class OrderController {

    @Resource
    private OrderHystrixService orderHystrixService;

    @GetMapping("/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        return orderHystrixService.paymentInfo_ok(id);
    }

    @GetMapping("/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = { // 自定义服务降级
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        return orderHystrixService.paymentInfo_timeout(id);
    }

    @GetMapping("/make-error")
    @HystrixCommand // 全局服务降级
    public String make_error(){
        int age = 10 / 0;
        return "no error";
    }

    // fallback
    public String paymentInfo_TimeoutHandler(Integer id) {
        return "fallback at order80 id: " + id;
    }

    // fallback
    public String global_fallback() {
        return "global_fallback as order80";
    }
}
