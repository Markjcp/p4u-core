package com.p4u.core.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.p4u.core.resource.CompanyResource;
import com.p4u.core.resource.ImageResource;
import com.p4u.core.resource.PreferenceResource;
import com.p4u.core.resource.PresentResource;
import com.p4u.core.resource.UserResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig(){
		register(UserResource.class);
		register(PresentResource.class);
		register(PreferenceResource.class);
		register(CompanyResource.class);
		register(ImageResource.class);
	}

}
