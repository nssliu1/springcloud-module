package com.nssliu.rabbitmq.rabbit.router;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/20 17:37
 * @describe:
 */
@Component
public class RouterSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    public void sendDelete() {
        String context = "我要删除了";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("routerExchange", "delete", context);
    }
    public void sendInsert() {
        String context = "我要增加了";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("routerExchange", "insert", context);
    }
}
