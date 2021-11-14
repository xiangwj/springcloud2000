package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.lang.UUID;
@RestController
public class PaymentController {

	@Value("${server.port}")
	private String serverPort;
	
	@Resource
	DiscoveryClient discoveryClient;
	
	@GetMapping("/payment/consul")
	public String paymentconsul() {
		return "springcloud with consul："+serverPort+"\t"+UUID.randomUUID().toString();
	}
	
}
