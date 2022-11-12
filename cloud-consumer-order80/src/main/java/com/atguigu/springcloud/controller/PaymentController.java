package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommentResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.atguigu.springcloud.entity.Payment;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer/payment")
public class PaymentController {

    //private static final String PAYMENT_URL = "http://localhost:8001/payment";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/payment";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public CommentResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/create", payment, CommentResult.class);
    }

    @GetMapping("/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id) {

        return restTemplate.getForObject(PAYMENT_URL + "/get/" + id, CommentResult.class);
    }

    @GetMapping("/getEntity/{id}")
    public CommentResult<Payment> getPaymentById2(@PathVariable("id") Long id) {

        ResponseEntity<CommentResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/get/" + id, CommentResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommentResult<>(444, "操作失败");
        }
    }
}
