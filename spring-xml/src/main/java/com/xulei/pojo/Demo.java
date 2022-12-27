package com.xulei.pojo;

public class Demo {
    public Demo() {
        System.out.println("Demo 无参构造器");
    }

    public void initMethod(){
        System.out.println("bean实例化之后调用init-method指定的方法");
    }

    public void destroyMethod(){
        System.out.println("bean实例销毁之后调用destroy-method指定的方法，需要主动close beanFactory");
    }
}
