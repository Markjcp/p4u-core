package com.p4u.core.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.p4u.core.resource.PresentResource;
import com.p4u.core.resource.UserResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig(){
		register(UserResource.class);
		register(PresentResource.class);
	}

}
