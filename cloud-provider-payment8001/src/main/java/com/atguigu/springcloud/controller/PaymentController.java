package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommentResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPost;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommentResult create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        if (res > 0) {
            return new CommentResult(200, "success", res);
        } else {
            return new CommentResult(444, "fail", null);
        }
    }

    @GetMapping("/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommentResult(200, "success,serverPost: " + serverPost, payment);
        } else {
            return new CommentResult(444, "fail", null);
        }
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println("elent:" + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId()+instance.getHost()+instance.getPort()+instance.getUri());
        }
        return discoveryClient;
    }
}
