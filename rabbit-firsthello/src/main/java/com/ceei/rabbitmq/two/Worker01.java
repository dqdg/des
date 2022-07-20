package com.ceei.rabbitmq.two;

import com.ceei.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @ClassName: Worker01
 * @Author: dqdgo
 * @Description: 这是一个工作线程（相当于消费者）
 * @Create: 2022-07-19 16:20
 **/
public class Worker01 {
    //队列名称
    public static final String QUEUE_NAME="hello";

    //接收消息
    public static void main(String[] args) throws Exception {
        Channel chanel = RabbitMqUtils.getChanel();

        //lambda表达式声明 接收消息
        DeliverCallback deliverCallback = (consumerTag, message)->{
            System.out.println("接收到的消息："+new String(message.getBody()));
        };
        //取消消息时的回调
        CancelCallback cancelCallback = consumerTag->{
            System.out.println(consumerTag+"消息者取消消费接口回调逻辑");
        };
        System.out.println("第一个工作线程C2等待接收消息.....");
        chanel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);


    }
}
