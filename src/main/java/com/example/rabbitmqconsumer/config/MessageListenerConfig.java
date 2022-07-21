package com.example.rabbitmqconsumer.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageListenerConfig {

    //rabbitmq链接工厂
    @Autowired
    private CachingConnectionFactory connectionFactory;

    //消息接收处理类
    @Autowired
    private MyAckReceiver myAckReceiver;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        //确认消费者数量
        container.setConcurrentConsumers(1);
        //最大消费者数量
        container.setMaxConcurrentConsumers(1);
        //RabbitMQ默认自动确认：AcknowledgeMode.NONE,这里改为手动确认消息
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        //设置一个队列
        container.setQueueNames("TestDirectQueue");

        //如果同时设置多个如下：前提队列必须已经创建存在的
        //container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");



        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("TestDirectQueue",true));
        //container.addQueues(new Queue("TestDirectQueue2",true));
        //container.addQueues(new Queue("TestDirectQueue3",true));



        container.setMessageListener(myAckReceiver);

        return container;

    }
}
