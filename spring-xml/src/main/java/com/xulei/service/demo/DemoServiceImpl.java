package com.xulei.service.demo;

import com.xulei.dao.demo.DemoDao;

public class DemoServiceImpl implements DemoService {
    // 构造函数
    public DemoServiceImpl() {
        System.out.println("DemoServiceImpl 无参构造");
    }

    public DemoServiceImpl(DemoDao demoDao) {
        System.out.println("基于构造器的注入：" + demoDao);
    }

    public DemoServiceImpl(String str) {
        System.out.println("基于构造器的注入(字符串)：" + str);
    }
    public DemoServiceImpl(String str, int age) {
        System.out.println("基于构造器的注入(多参数)：" + str+",age="+age);
    }

    // setXXX方式注入
    public void setDemoDao(DemoDao demoDao) {
        System.out.println("set方法注入，setDemoDao:" + demoDao);
    }
    public void setName(String name) {
        System.out.println("set方法注入，setName:" + name);
    }

    // 测试从properties文件注入
    public void setUsername(String username) {
        System.out.println("set方法注入，username:" + username);
    }

}
