package com.xulei.dao.demo;

import org.springframework.stereotype.Repository;

@Repository
public class DemoDaoImpl implements DemoDao {
    public DemoDaoImpl(){
        System.out.println("DemoDaoImpl 无参构造");
    }

}
