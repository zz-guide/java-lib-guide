package com.xulei.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.xulei")
@PropertySource("classpath:db.properties")
public class ApplicationContextConfig {
}
