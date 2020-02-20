package com.nssliu.feigncustomer.controller;

import com.nssliu.entity.User;
import com.nssliu.feigncustomer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/19 19:11
 * @describe:
 */
@RestController
@RequestMapping("/testController")
public class TestController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/testinterfacebyfeign")
    public String sayHi(@RequestParam String name) {
        return userService.getValue( name );
    }
    @RequestMapping(value = "/getUserClient")
    public User getUserClient(@RequestParam String name) {
        return userService.getUser(name);
    }


}

