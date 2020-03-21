package com.nssliu.rabbitmq.rabbit.topic3;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/20 16:55
 * @describe:
 */


        import org.springframework.amqp.rabbit.annotation.RabbitHandler;
        import org.springframework.amqp.rabbit.annotation.RabbitListener;
        import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_topic_message")
public class Receiver1 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }
}

