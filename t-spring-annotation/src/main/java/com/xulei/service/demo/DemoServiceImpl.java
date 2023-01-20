package com.xulei.service.demo;

import com.xulei.dao.demo.DemoDao;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    // 构造函数
    public DemoServiceImpl() {
        System.out.println("DemoServiceImpl 无参构造");
    }

    // setXXX方式注入
    public void setDemoDao(DemoDao demoDao) {
        System.out.println("set方法注入，setDemoDao:" + demoDao);
    }

}
