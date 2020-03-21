package com.nssliu.rabbitmq.rabbit.release4;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_fanout_C")
public class RecvC {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("CReceiver  : " + hello + "/n");
    }
}
