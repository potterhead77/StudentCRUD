package com.example.demo.student.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
        public String homepage(){
            return "index";
        }
}
