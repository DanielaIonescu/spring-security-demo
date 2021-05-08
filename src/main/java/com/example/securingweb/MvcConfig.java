package com.example.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.*;
import java.util.Arrays;

@Configuration
public class MvcConfig implements WebMvcConfigurer, Serializable {

    Console c = System.console();

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");




    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

}
