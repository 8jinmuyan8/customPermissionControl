package com.lx.project.controller;

import com.lx.project.entity.SysUser;
import com.lx.project.service.ISysUserService;
import com.lx.project.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//
//@RequestMapping
//@RestController
//public class TestController extends BaseController {
//    @Autowired
//    ISysUserService userService;
//
//    @GetMapping("/")
//    public String hello(){
//      SysUser sysUser = userService.selectByAccount("lx");
//
//        return JacksonUtils.toJson(sysUser);
//    }
//    @GetMapping("/hello")
//    public String test(){
//        SysUser sysUser = userService.selectByAccount("lx");
//
//        return "hello";
//    }
//}
