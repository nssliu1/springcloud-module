package com.nssliu.rabbitmq.rabbit.release4;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_fanout_B")
public class RecvB {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("BReceiver  : " + hello + "/n");
    }
}
