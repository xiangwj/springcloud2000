package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController {
	@Resource
	PaymentService paymentService;
	
	@Value("${server.port}")
	String serverPort;
	
	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentinfo_ok(@PathVariable("id") Integer id) {
		String result =  paymentService.paymentInfo_OK(id);
		log.info("****result:"+result);
		return result;
	}
	@GetMapping("/payment/hystrix/timeout/{id}")
	public String paymentinfo_timeout(@PathVariable("id") Integer id) {
		String result =  paymentService.paymentInfo_timeout(id);
		log.info("****result:"+result);
		return result;
	}
	@GetMapping("/payment/hystrix/circuit/{id}")
	public String paymentCircuitBraker(@PathVariable("id") Integer id) {
		String result =  paymentService.paymentCircuitBraker(id);
		return result;
	}
		
}
