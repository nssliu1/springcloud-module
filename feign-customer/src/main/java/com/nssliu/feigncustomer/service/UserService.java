package com.nssliu.feigncustomer.service;

import com.nssliu.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/20 10:01
 * @describe:
 */
@FeignClient(value = "service-hi")//表明哪一个服务
public interface UserService {

    //该user依赖公共依赖库
    @RequestMapping(value = "/servers/getUser")//服务中的url
    User getUser(@RequestParam("name") String name);

    @RequestMapping(value = "/servers/getValue")
    String getValue(@RequestParam(value = "name") String name);

}
