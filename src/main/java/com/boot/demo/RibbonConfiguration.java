package com.boot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IRule;

public class RibbonConfiguration {
	
	@Autowired
	IClientConfig clientConfig;	
	
	@Bean
	public IRule getRule(IClientConfig clientConfig)
	{
		return new AvailabilityFilteringRule();
	}
}
