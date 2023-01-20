package com.xulei.boot01.config;

import com.xulei.boot01.resolver.MyLocaleResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
@ConfigurationProperties(prefix = "spring.validator")
public class ValidatorConfig {

    private String failFast = "true";

    public String getFailFast() {
        return failFast;
    }

    public void setFailFast(String failFast) {
        this.failFast = failFast;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.getValidationPropertyMap().put("hibernate.validator.fail_fast", failFast);
        return factoryBean;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
