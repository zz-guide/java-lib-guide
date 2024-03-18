package com.xulei.boot01.controller;

import com.xulei.boot01.dto.Student;
import com.xulei.boot01.dto.User;
import com.xulei.common.util.CommonResponse;
import com.xulei.common.util.Response;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/student")
@Validated
public class StudentController {
    @GetMapping(value = "/get/{id}")
    public String get(@PathVariable Long id){
        System.out.println("id:"+id);
        return "hello";
    }

    @GetMapping(value = "/get1/{id}")
    public Response<Object> get1(@PathVariable Long id){
        System.out.println("id:"+id);
        return CommonResponse.success();
    }

    @GetMapping(value = "/get2/{id}")
    public Map<String,Object> get2(@PathVariable Long id){
        System.out.println("id:"+id);

        Map<String, Object> map= new HashMap<>();
        map.put("code", 200);
        map.put("msg", "success");
        return map;
    }

    @GetMapping(value = "/get3/{id}")
    public List<String> get3(@PathVariable Long id){
        System.out.println("id:"+id);

        List<String> list= new ArrayList<>();
        list.add("许磊");
        list.add("张三");
        list.add("李四");
        return list;
    }

    @GetMapping(value = "/throw1")
    public boolean throw1(){
        int a = 1/0;
        return true;
    }

    @PostMapping(value = "/validate1")
    public boolean validate1(@RequestBody @Validated User user){
        System.out.println("------validate1------"+user);
        return true;
    }

    @PostMapping(value = "/validate2")
    public boolean validate2(@RequestBody @Valid Student student){
        System.out.println("------validate2------"+student);
        return true;
    }
}
