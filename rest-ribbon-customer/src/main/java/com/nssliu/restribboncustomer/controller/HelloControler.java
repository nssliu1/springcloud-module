package com.nssliu.restribboncustomer.controller;


import com.nssliu.restribboncustomer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/19 17:30
 * @describe:
 */

@RestController
@RequestMapping("/helloController")
public class HelloControler
{

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name)
    {
        return helloService.hiService(name);
    }
    @RequestMapping(value = "/hiObject")
    public Object hiObject(@RequestParam String name)
    {
        return helloService.hiObject(name);
    }
}