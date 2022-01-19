package com.example.springbootstudy02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/index.html","/"})   //地址栏输入这俩，会跳转到index.html运行
    public String visitIndex(){
       return "index";
    }
}
 