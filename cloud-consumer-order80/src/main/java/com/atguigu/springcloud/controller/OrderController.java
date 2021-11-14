package com.atguigu.springcloud.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderController {
	public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE/"; 
	@Resource
	RestTemplate restTemplate;
	@Resource
	private LoadBalancer loadBalancer;
	@Resource
	private DiscoveryClient discoveryClient;
	@GetMapping(value="/consumer/payment/create")
	public CommonResult<Payment> create(Payment payment) {
		return restTemplate.postForObject(PAYMENT_URL+"payment/create", payment, CommonResult.class);
	}
	@GetMapping(value="/consumer/payment/get/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
		return restTemplate.getForObject(PAYMENT_URL+"payment/get/"+id, CommonResult.class);
	}
	@GetMapping(value="/consumer/payment2/get/{id}")
	public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
		 ResponseEntity<CommonResult> entity =restTemplate.getForEntity(PAYMENT_URL+"payment/get/"+id, CommonResult.class);
		 if(entity.getStatusCode().is2xxSuccessful()) {
			 return entity.getBody();
		 }else {
			 return new CommonResult<>(444,"操作失败");
		 }
	}
	@GetMapping("/consumer/payment/lb")
	public String getPaymentLB() {
		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		if(instances==null || instances.isEmpty()) {
			return null;
		}else {
			ServiceInstance instance =loadBalancer.instances(instances);
			URI uri = instance.getUri();
			return restTemplate.getForObject(uri+"/payment/lb",String.class);
		}
	}	
}
