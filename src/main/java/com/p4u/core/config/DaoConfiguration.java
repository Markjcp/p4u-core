package com.p4u.core.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.p4u.core")
@EntityScan("com.p4u.core.model")
public class DaoConfiguration {

}
