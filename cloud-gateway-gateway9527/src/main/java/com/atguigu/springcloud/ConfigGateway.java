package com.atguigu.springcloud;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigGateway {
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {
		Builder routes =routeLocatorBuilder.routes();
		routes.route("path_route_atguigu",
				r->r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
		routes.route("path_route_atguigu1",
				r->r.path("/mil").uri("http://news.baidu.com/mil")).build();		
		return routes.build();
	}
}
