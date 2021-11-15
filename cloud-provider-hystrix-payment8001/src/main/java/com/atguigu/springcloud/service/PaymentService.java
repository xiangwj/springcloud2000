package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import cn.hutool.core.util.IdUtil;

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
	@HystrixCommand(fallbackMethod = "paymentinfotimeouthandler",commandProperties = {
			@HystrixProperty(name="circuitBreaker.enabled",value="true"),//是否开启断路器
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),//请求次数
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间窗口
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60")//错误率
			
			
	})	
	public String paymentCircuitBraker(Integer id) {
		if(id<0) {
			throw new RuntimeException("ID*****不能为负");
		}
		String serialnumber= IdUtil.simpleUUID();
		return Thread.currentThread().getName()+"\t"+"调用成功：流水号为："+serialnumber;
	}
}
