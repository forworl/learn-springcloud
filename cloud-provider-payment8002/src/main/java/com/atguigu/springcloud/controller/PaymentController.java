package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommentResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPost;

    @PostMapping("/create")
    public CommentResult create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        if (res > 0) {
            return new CommentResult(200, "success,+serverPost:" + serverPost, res);
        } else {
            return new CommentResult(444, "fail", null);
        }
    }

    @GetMapping("/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommentResult(200, "success,serverPost: "+serverPost, payment);
        } else {
            return new CommentResult(444, "fail", null);
        }
    }
}
