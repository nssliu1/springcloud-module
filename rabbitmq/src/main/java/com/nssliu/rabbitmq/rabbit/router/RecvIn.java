package com.nssliu.rabbitmq.rabbit.router;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/20 17:38
 * @describe:
 */
@Component
@RabbitListener(queues = "insert")
public class RecvIn {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("insert  : " + hello);
    }
}
