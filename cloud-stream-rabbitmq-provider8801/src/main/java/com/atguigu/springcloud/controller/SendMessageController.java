package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.service.IMessageProvider;

@RestController
public class SendMessageController {
	@Resource
	private IMessageProvider messageProvider;
	@GetMapping(value="/sendMessage")
	public String sendMessage() {
		return messageProvider.send();
	}
}
