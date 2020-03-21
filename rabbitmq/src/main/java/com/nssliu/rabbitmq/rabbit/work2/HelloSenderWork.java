package com.nssliu.rabbitmq.rabbit.work2;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/20 16:10
 * @describe:发送者
 */
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HelloSenderWork {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        System.out.println("Sender : " + context);
        //简单对列的情况下routingKey即为Q名
        for (int i=0;i<100;i++){

            this.rabbitTemplate.convertAndSend("q_hello_work", context);
        }
    }
}

