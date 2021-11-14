package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController {
	@Resource
	PaymentService paymentService;
	@Value("${server.port}")
	private String serverPort;
	@PostMapping(value="/payment/create")
	public CommonResult<Payment> create(@RequestBody Payment payment) {
		Long result = paymentService.create(payment);
		log.info("*****插入结果:"+result);
		if(result>0) {
			return new CommonResult(200,"插入数据库成功"+serverPort,result);
		}else {
			return new CommonResult(444,"插入数据库失败"+serverPort,null);
		}
	}
	@GetMapping(value="/payment/get/{id}")
	public CommonResult<Payment> create(@PathVariable Long id) {
		Payment result = paymentService.getPaymentById(id);
		log.info("*****查询结果:"+result);
		if(result!=null) {
			return new CommonResult(200,"查询成功"+serverPort,result);
		}else {
			return new CommonResult(444,"没有对应记录"+serverPort,null);
		}
	}
	@GetMapping("/payment/lb")
	public String getPaymentLB() {
		return ""+serverPort;
	}	
}
