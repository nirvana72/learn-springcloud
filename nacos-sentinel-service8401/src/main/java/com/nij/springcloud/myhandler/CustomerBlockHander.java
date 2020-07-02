package com.nij.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nij.springcloud.entities.CommonResult;

public class CustomerBlockHander {
    public static CommonResult handlerException1(BlockException exception) {
        return new CommonResult(444, "自定义1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "自定义2");
    }

}
