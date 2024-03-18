package com.xulei.boot01.controller;

import com.xulei.boot01.pojo.User;
import com.xulei.boot01.service.UserService;
import com.xulei.common.util.CommonResponse;
import com.xulei.common.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Validated
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/list")
    public Response list(){
        System.out.println("-----list----");
        List<User> userList = userService.list();
        return CommonResponse.successWithData(userList);
    }
}
