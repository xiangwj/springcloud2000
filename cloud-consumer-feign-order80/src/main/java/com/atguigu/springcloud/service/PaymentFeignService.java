package com.atguigu.springcloud.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
@Component
@FeignClient(value="cloud-payment-service")
public interface PaymentFeignService {
	@PostMapping(value="/payment/create")
	public CommonResult<Payment> create(@RequestBody Payment payment);
	@GetMapping(value="/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
	@GetMapping("/payment/timeout")
	public String paymentTimeOut();
}
