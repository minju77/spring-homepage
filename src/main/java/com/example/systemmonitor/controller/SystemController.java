package com.example.systemmonitor.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

    @PostMapping("/system")
    public String receiveData(@RequestBody Map<String, Object> data) {
        System.out.println(data);
        return "ok";
    }
}
