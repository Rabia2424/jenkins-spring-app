package com.book.jenkins.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HelloController {

    @GetMapping("")
    public String hello(){
        return "hello the Jenkins world, you are successfull!";
    }
}
