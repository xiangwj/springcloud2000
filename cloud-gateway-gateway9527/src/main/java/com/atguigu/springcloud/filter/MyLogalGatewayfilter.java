package com.atguigu.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import cn.hutool.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
//@Component
@Slf4j
public class MyLogalGatewayfilter implements GlobalFilter, Ordered{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("*******来了*****");
		String username= exchange.getRequest().getQueryParams().getFirst("username");
		if(username==null) {
			exchange.getResponse().setRawStatusCode(HttpStatus.HTTP_NOT_ACCEPTABLE);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
