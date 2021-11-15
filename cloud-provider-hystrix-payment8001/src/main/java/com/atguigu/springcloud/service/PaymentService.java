package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class PaymentService {
	public String paymentInfo_OK(Integer id) {
		return "线程池:"+Thread.currentThread().getName()+" paymentInfo_OK\t"+id+"哈哈";
	}
	@HystrixCommand(fallbackMethod = "paymentinfotimeouthandler",commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
			
			
	})
	public String paymentInfo_timeout(Integer id) {
		int seconds=3;
		
		try {
			
			Thread.sleep(seconds*1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "线程池:"+Thread.currentThread().getName()+" paymentInfo_timeout\t"+id+"哈哈,耗时:"+seconds+"秒";
	}
	
	public String paymentinfotimeouthandler(Integer id) {
		return "线程池:"+Thread.currentThread().getName()+" paymentinfotimeouthandler\t"+id+"呜呜";
	}
}
