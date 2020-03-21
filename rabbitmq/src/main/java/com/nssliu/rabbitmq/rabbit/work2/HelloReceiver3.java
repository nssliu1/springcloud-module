package com.nssliu.rabbitmq.rabbit.work2;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/20 16:11
 * @describe:接受者
 */
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_hello_work")
public class HelloReceiver3 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver3  : " + hello);
    }
}

