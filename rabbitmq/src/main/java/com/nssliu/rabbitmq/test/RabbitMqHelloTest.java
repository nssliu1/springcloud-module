package com.nssliu.rabbitmq.test;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/20 16:13
 * @describe:
 */
import com.nssliu.rabbitmq.rabbit.release4.Sender;
import com.nssliu.rabbitmq.rabbit.router.RouterSender;
import com.nssliu.rabbitmq.rabbit.simple1.HelloSender;
import com.nssliu.rabbitmq.rabbit.topic3.MsgSender;
import com.nssliu.rabbitmq.rabbit.work2.HelloSenderWork;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;
    @Autowired
    private HelloSenderWork helloSenderWork;
    @Autowired
    private MsgSender msgSender;
    @Autowired
    private Sender sender;
    @Autowired
    private RouterSender routerSender;

    @Test
    public void hello() throws Exception {
        //helloSender.send();
        /*msgSender.send1();
        msgSender.send2();*/
        //sender.send();

        routerSender.sendDelete();
        routerSender.sendInsert();
    }
}

