package com.xulei.boot01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xulei.boot01.mapper")
@SpringBootApplication
public class Boot01Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot01Application.class, args);
    }

}
