package com.nssliu.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterRabbitConfig {

    final static String queuequeue = "insert";
    final static String queuequeues = "delete";

    @Bean
    public Queue queuequeue() {
        return new Queue(RouterRabbitConfig.queuequeue);
    }

    @Bean
    public Queue queuequeues() {
        return new Queue(RouterRabbitConfig.queuequeues);
    }

    /**
     * 声明一个Topic类型的交换机
     * @return
     */
    @Bean
    DirectExchange exchanges() {
        return new DirectExchange("routerExchange");
    }

    /**
     * 绑定Q到交换机,并且指定routingKey
     * @param queuequeue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessagess(Queue queuequeue, DirectExchange exchange) {
        return BindingBuilder.bind(queuequeue).to(exchange).with("insert");
    }

    @Bean
    Binding bindingExchangeMessagesss(Queue queuequeues, DirectExchange exchange) {
        return BindingBuilder.bind(queuequeues).to(exchange).with("delete");
    }
}
