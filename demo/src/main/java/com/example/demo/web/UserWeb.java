package com.example.demo.web;


import com.example.demo.logs.LogInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserWeb {




    @LogInfo
    @RequestMapping("/test")
    public String test(){

        return "eeee";
    }

}
