package com.atguigu.springcloud.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController {
	@Resource
	PaymentService paymentService;
	@Value("${server.port}")
	private String serverPort;
	
	@Resource
	DiscoveryClient discoveryClient;
	
	@GetMapping("/payment/zk")
	public String paymentzk() {
		return "springcloud with zookeeper："+serverPort+"\t"+UUID.randomUUID().toString();
	}
	

}
