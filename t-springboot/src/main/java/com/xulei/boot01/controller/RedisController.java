package com.xulei.boot01.controller;

import com.xulei.common.util.CommonResponse;
import com.xulei.common.util.Response;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    RedissonClient redissonClient = Redisson.create();

    @GetMapping(value = "/redis/t")
    public Response<Object> list(){
        System.out.println("-----redis----");

        RMap<String, String> value = redissonClient.getMap("name");
        System.out.println("key=name,value="+value);
        return CommonResponse.success();
    }
}
