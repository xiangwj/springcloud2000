package com.atguigu.springcloud.service.impl;


import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.atguigu.springcloud.service.IMessageProvider;
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider{
	@Resource
	private MessageChannel output;
	@Override
	public String send() {
		String serial = UUID.randomUUID().toString();
		output.send(MessageBuilder.withPayload(serial).build());
		System.out.println("****serial:"+serial);
		return null;
	}

}
