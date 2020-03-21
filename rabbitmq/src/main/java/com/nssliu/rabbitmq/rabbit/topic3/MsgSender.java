package com.nssliu.rabbitmq.rabbit.topic3;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/20 16:57
 * @describe:
 */

        import org.springframework.amqp.core.AmqpTemplate;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;

@Component
public class MsgSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("mybootexchange", "topic.message", context);
    }


    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("mybootexchange", "topic.messages", context);
    }
}

