package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommentResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 感觉像是二重接口，
 * 第一重引导，
 * 第二重转发（相当于原来的用restTemplate的controller层）
 */
@RestController
@RequestMapping("/consumer/payment")
public class PaymentController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id) {

        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/feign/timeout")
    public String feignTimeout(){
        return paymentFeignService.feignTimeout();
    }

}
