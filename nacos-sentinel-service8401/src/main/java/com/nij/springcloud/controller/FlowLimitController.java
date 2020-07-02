package com.nij.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA () {
        return "------------testA";
    }

    @GetMapping("/testB")
    public String testB () {
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "------------testB";
    }

    @GetMapping("/testH")
    @SentinelResource(value = "testH", blockHandler = "deal_testH")
    public String testHotKey(@RequestParam(value = "p1", required = false, defaultValue = "p1") String p1,
                             @RequestParam(value = "p2", required = false, defaultValue = "p2") String p2) {
        return "------------testH, p1 = " + p1 + ", p2 = " + p2;
    }

    public String deal_testH(String p1, String p2, BlockException exception) {
        return "------------deal_testH, p1 = " + p1 + ", p2 = " + p2;
    }
}
