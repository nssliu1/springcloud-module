package com.nssliu.server;

import com.nssliu.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/19 17:13
 * @describe:
 */
@RestController
@RequestMapping("/servers")
public class Servers {
    @Value("${server.port}")
    String port;
    @RequestMapping("/getValue")
    public String getValue(@RequestParam String name) throws InterruptedException {
        Thread.sleep(10000);
        return "hi"+name+port;
    }
    @RequestMapping("/getObject")
    public Object getObject(@RequestParam String name) throws InterruptedException {
        Thread.sleep(10000);
        User user = new User();
        user.setName(name);
        user.setAge("33");
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("nssliu",user);
        return objectObjectHashMap;

    }
    @RequestMapping("/getUser")
    public User getUser(@RequestParam String name){
        User user = new User();
        user.setName(name);
        user.setAge("13");
        return user;
    }
}
