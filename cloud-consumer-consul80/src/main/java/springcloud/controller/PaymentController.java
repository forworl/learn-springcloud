package springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer/payment")
public class PaymentController {

    //private static final String PAYMENT_URL = "http://localhost:8001/payment";
    private static final String PAYMENT_URL = "http://consul-provider-payment/payment";

    @Resource
    private RestTemplate restTemplate;


    @GetMapping("/consul")
    public String paymentInfo() {

        return restTemplate.getForObject(PAYMENT_URL + "/consul", String.class);
    }
}
