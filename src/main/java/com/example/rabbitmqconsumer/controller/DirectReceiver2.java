package com.example.rabbitmqconsumer.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
//@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiver2 {



    //多台监听绑定到同一个直连交互的同一个队列，消息会一条一条轮番发送，一次一条
    @RabbitHandler
    public void process(Map testMessage){
        System.out.println("第二个---DirectReceiver消费者收到的信息为："+testMessage.toString());
    }

}
