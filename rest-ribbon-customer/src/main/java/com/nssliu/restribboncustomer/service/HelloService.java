package com.nssliu.restribboncustomer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/19 17:32
 * @describe:
 */
@Service
public class HelloService
{
    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name)
    {
        return restTemplate.getForObject("http://SERVICE-HI/servers/getValue?name=" + name, String.class);
    }

    public Object hiObject(String name){
        return restTemplate.getForObject("http://SERVICE-HI/servers/getObject?name=" + name, Object.class);
    }
}
