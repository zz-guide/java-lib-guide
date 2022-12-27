package com.xulei;

import com.xulei.pojo.Demo;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试Spring获取Bean的方式
 */
public class TestLoadBeanFactory {

    @Test
    public void useBeanFactory(){
        String[] path = {"applicationContext.xml"};
        // 创建工厂对象
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 通过bean工厂创建一个读取xml文件的对象
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 读取xml文件
        xmlBeanDefinitionReader.loadBeanDefinitions(path);
        // 根据id或者name获取bean对象
        Demo user = (Demo)beanFactory.getBean("user");//发现调用了无参构造
        System.out.println(user);
    }

    @Test
    public void useApplicationContext(){
        String[] path = {"applicationContext.xml"};
        // 使用更高级的封装ApplicationContext进行bean对象的获取
        // 默认容器启动的时候就会创建bean对象
        // 底层其实使用的还是BeanFactory接口
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        Demo user = (Demo)context.getBean("user");
        System.out.println(user);
    }
}
