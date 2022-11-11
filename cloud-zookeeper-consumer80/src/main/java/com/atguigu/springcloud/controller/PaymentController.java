package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommentResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer/payment")
public class PaymentController {

    //private static final String PAYMENT_URL = "http://localhost:8001/payment";
    private static final String PAYMENT_URL = "http://cloud-provider-payment/payment";

    @Resource
    private RestTemplate restTemplate;


    @GetMapping("/zk")
    public String paymentInfo() {

        return restTemplate.getForObject(PAYMENT_URL + "/zk", String.class);
    }
}
