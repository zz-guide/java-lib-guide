package com.xulei.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;

/**
 * bean factory 后置处理器
 * 作用：可以在创建bean实例之前，动态修改bean定义的属性值；重点在于修改
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
    }
}
