package com.p4u.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.p4u.core.service.PresentSelectorService;

@Configuration
public class ServiceConfiguration {
	
	@Bean(name="presentSelectorService")
	public PresentSelectorService getPresentSelectorService(){
		return new PresentSelectorService();
	}

}
