package com.stu.sbt.modules.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
public class HelloController {

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "Hello World!!";
    }
}
