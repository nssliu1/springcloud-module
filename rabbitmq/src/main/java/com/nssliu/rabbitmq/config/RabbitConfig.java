package com.nssliu.rabbitmq.config;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/20 16:09
 * @describe:
 */
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitConfig {
    //@Bean
    public Queue queue() {
        return new Queue("q_hello");
    }
}

