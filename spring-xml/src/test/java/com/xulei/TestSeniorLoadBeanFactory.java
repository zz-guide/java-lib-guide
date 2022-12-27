package com.xulei;

import com.xulei.dao.demo.DemoDao;
import com.xulei.service.demo.DemoService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试Spring IOC, DI
 */
public class TestSeniorLoadBeanFactory {
    static ApplicationContext context;
    static {
        String[] path = {"applicationContext.xml"};
        context = new ClassPathXmlApplicationContext(path);
    }

    @Test
    public void testDi(){
        // 测试一般属性注入
//        DemoService demoService = (DemoService) context.getBean("demoService");
//        System.out.println(demoService);

//        UserDao userDao = (UserDao) context.getBean("userDao");
//        System.out.println(userDao);
    }

    @Test
    public void testSetDi(){
        // 测试集合属性注入
    }

    @Test
    public void testGetBean(){
        // 获取bean的几种方式

        // 第一种, 根据id或者name获取，需要强转
        DemoDao demoDao = (DemoDao) context.getBean("demoDao");
        System.out.println(demoDao);
        // 第二种, 根据id或者name指定class,不需要强转
        DemoDao demoDao1 = context.getBean("userDao", DemoDao.class);
        System.out.println(demoDao1);

        // 第三种, 要求不能有多个类型的bean
        DemoDao demoDao2 = context.getBean(DemoDao.class);
        System.out.println(demoDao2);
    }

    @Test
    public void test2(){
//        String[] path = {"applicationContext.xml"};
//        ApplicationContext context = new ClassPathXmlApplicationContext(path);
//        StudentService studentService = (StudentServiceImpl) context.getBean("studentServiceImpl");
//        System.out.println(studentService.getStudent());
    }

    @Test
    public void test3(){
//        String[] path = {"applicationContext.xml"};
//        ApplicationContext context = new ClassPathXmlApplicationContext(path);
//        StudentService studentService = (StudentServiceImpl) context.getBean("studentServiceImpl2");
//        System.out.println(studentService.getStudent1());
    }

    @Test
    public void test4(){
//        String[] path = {"applicationContext.xml"};
//        ApplicationContext context = new ClassPathXmlApplicationContext(path);
//        StudentService studentService = (StudentServiceImpl) context.getBean("studentServiceImpl2");
//        StudentService studentService1 = (StudentServiceImpl) context.getBean("aaa");
//        StudentService studentService2 =  context.getBean("aaa", StudentServiceImpl.class);
//        // 默认情况下的对象是单例的
//        System.out.println(studentService.hashCode());
//        System.out.println(studentService1.hashCode());
//        System.out.println(studentService2.hashCode());
    }

    @Test
    public void test5(){
//        String[] path = {"applicationContext.xml"};
//        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        // 测试所有类型的注入,bean | ref | idref | list | set | map | props | value | null
//        Teacher teacher = (Teacher) context.getBean("teacher");
//        System.out.println(teacher);

//        Teacher teacherConstructor = (Teacher) context.getBean("teacherConstructor");
//        System.out.println(teacherConstructor);
    }

    @Test
    public void test6(){
        // singleton 单例 prototype 原型模式
//        String[] path = {"applicationContext.xml"};
//        ApplicationContext context = new ClassPathXmlApplicationContext(path);
//        User user = context.getBean("user", User.class);
//        User user1 = context.getBean("user", User.class);
//
//        System.out.println("user:"+user.hashCode());
//        System.out.println("user1:"+user1.hashCode());
//
//        user1.setName("许磊");
//        System.out.println("user1"+user1);
//        System.out.println("user:"+user);
//
//        User userRepeat = context.getBean("userRepeat", User.class);
//        User userRepeat1 = context.getBean("userRepeat", User.class);
//        System.out.println("userRepeat:"+userRepeat.hashCode());
//        System.out.println("userRepeat1:"+userRepeat1.hashCode());
    }

    @Test
    public void test7(){
//        String[] path = {"applicationContext.xml"};
//        ApplicationContext context = new ClassPathXmlApplicationContext(path);
//        User userRepeat1 = context.getBean("userRepeat", User.class);
    }
}
