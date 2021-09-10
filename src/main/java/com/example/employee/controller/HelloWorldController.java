package com.example.employee.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pguo
 * @Date: 2021/9/8 19:33
 */
@RestController
@EnableAutoConfiguration
public class HelloWorldController {

    @RequestMapping("/sayHello")
    public String sayHello(){
         return "Hello, SpringBoot Unit Test";
    }

}
