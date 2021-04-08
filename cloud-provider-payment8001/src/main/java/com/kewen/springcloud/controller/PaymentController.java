package com.kewen.springcloud.controller;

import com.kewen.springcloud.entities.CommonResult;
import com.kewen.springcloud.entities.Payment;
import com.kewen.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment")
    public CommonResult create(Payment payment) {
        int result = paymentService.create(payment);
        log.info("==>创建支付结果：" + result);
        if(result > 0) {
            return new CommonResult(200, "创建支付成功", result);
        } else {
            return new CommonResult(400, "创建支付失败", null);
        }
    }

    @GetMapping("/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("==>查询结果：" + payment);
        if(Objects.isNull(payment)) {
            return new CommonResult(400, "没有查询到记录，查询的ID：" + id, null);
        } else {
            return new CommonResult(200, "查询成功", payment);
        }
    }
}
