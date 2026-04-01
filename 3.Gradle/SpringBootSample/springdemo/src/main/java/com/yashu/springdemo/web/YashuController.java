package com.yashu.springdemo.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class YashuController {
    @GetMapping("/")
    public String getInfo() {
        return "Hello World!";
    }
}
