package com.xulei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 全注解
 */
@Controller
public class HelloController2 {
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("msg", "hello");
        return "hello";
    }
}
