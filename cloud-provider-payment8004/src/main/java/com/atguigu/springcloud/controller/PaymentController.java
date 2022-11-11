package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommentResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {


    @Value("${server.port}")
    private String serverPost;

    @GetMapping("/zk")
    public String paymentzk() {
        return "zookeeper" + serverPost + " " + UUID.randomUUID().toString();
    }
}
