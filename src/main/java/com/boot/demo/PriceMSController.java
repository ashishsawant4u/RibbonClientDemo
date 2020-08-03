package com.boot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RibbonClient(name = "price-ms-loadbalancer",configuration = RibbonConfiguration.class)
@RequestMapping("/ribbonLB")
@RestController
public class PriceMSController {

	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(RestTemplateBuilder builder)
	{
		return builder.build();
	}
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@RequestMapping(value = "/price/{code}",method = RequestMethod.GET)
	public String getPriceForProduct(@PathVariable("code") String productCode)
	{
		String priceMSUrl = "http://price-ms-loadbalancer/pricingMS/getPrice";
		
		ResponseEntity<String> resp =  restTemplate.postForEntity(priceMSUrl, productCode,String.class);
		
		return resp.getBody();
	}
	
}
