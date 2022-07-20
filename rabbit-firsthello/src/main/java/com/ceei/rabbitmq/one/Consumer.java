package com.ceei.rabbitmq.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName: Consumer
 * @Author: dqdgo
 * @Description:消费者 接收消息
 * @Create: 2022-07-19 10:04
 **/
public class Consumer {
    //队列名称
    public static final String QUEUE_NAME="hello";
    //接收消息
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置工厂IP\用户名、密码 连接RabbitMQ队列
        factory.setHost("192.168.6.158");
        factory.setUsername("admin");
        factory.setPassword("123");
        //创建连接
        Connection connection = factory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();
        /**
         * 消费者消费消息
         * 参数：
         * 1.消费哪个队列
         * 2.消费成功后是否要自动应答（true自动应答，false手动应答）
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        //lambda表达式声明 接收消息
        DeliverCallback deliverCallback = (consumerTag,message)->{
            System.out.println(new String(message.getBody()));
        };
        //取消消息时的回调
        CancelCallback cancelCallback = consumerTag->{
            System.out.println("消息消费被中断");
        };
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);

    }
}
