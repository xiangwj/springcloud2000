package com.atguigu.springcloud.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;

@Service
public class PaymentServiceImpl implements PaymentService{
	@Resource
	PaymentDao paymentDao;

	@Override
	public Long create(Payment payment) {
		paymentDao.create(payment);
		return payment.getId();
	}

	@Override
	public Payment getPaymentById(Long id) {
		return paymentDao.getPaymentById(id);
	}

}
