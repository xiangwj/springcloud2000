package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceCallBack implements PaymentHystrixService{

	@Override
	public String paymentinfo_ok(Integer id) {
		// TODO Auto-generated method stub
		return "PaymentHystrixServiceCallBack:paymentinfo_ok fallback";
	}

	@Override
	public String paymentinfo_timeout(Integer id) {
		// TODO Auto-generated method stub
		return "PaymentHystrixServiceCallBack:paymentinfo_timeout callback";
	}

}
