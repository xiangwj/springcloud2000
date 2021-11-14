package com.atguigu.springcloud.lb;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
@Component
public class MyLB implements LoadBalancer{
	private AtomicInteger atomicInteger = new AtomicInteger(0);

	@Override
	public ServiceInstance instances(List<ServiceInstance> instances) {
		int idx = getAndIncrement() %instances.size();
		return instances.get(idx);
	}
	public final int getAndIncrement() {
		int current=0;
		int next = 0;
        do {
            current = atomicInteger.get();
            next =  current>=Integer.MAX_VALUE? 0:  current + 1;
        }while(!atomicInteger.compareAndSet(current, next));
        return next;
	}
}
