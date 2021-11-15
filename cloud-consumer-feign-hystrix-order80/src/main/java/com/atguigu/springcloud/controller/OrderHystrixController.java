package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@DefaultProperties(defaultFallback = "payment_Global_Fallbackhandler")
public class OrderHystrixController {
	@Resource
	PaymentHystrixService paymentHystrixService;
	
	@GetMapping("/consumer/payment/hystrix/ok/{id}")
	public String paymentinfo_ok(@PathVariable("id") Integer id) {
		return paymentHystrixService.paymentinfo_ok(id);
	}
	@GetMapping("/consumer/payment/hystrix/timeout/{id}")
	/*@HystrixCommand(fallbackMethod = "paymentinfotimeouthandler",commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
			
			
	})*/
	@HystrixCommand
	public String paymentinfo_timeout(@PathVariable("id") Integer id) {
		return paymentHystrixService.paymentinfo_timeout(id);
	}
	public String paymentinfotimeouthandler(Integer id) {
		return "线程池:"+Thread.currentThread().getName()+" 我是客户端paymentinfotimeouthandler\t"+id+"呜呜";
	}
	public String payment_Global_Fallbackhandler() {
		return "线程池:"+Thread.currentThread().getName()+" 我是客户端全局的paymentinfotimeouthandler\t"+"呜呜";
	}
}
