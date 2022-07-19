package com.ceei.rabbitmq.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者：发消息
 */
public class Producer {
    //发送队列名称
    public static final String QUEUE_NAME="hello";

    //发消息
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
        //生成一个队列
        // 参数：1.队列名称;2.队列里面的消息是否持久化（存储在磁盘中），默认情况消息存储在内存中；
        // 3.该队列是否只供一个消费者进行消费，是否进行消息共享，true可以多个消费者消费，默认false
        //4.最后一个消费者端开连接亿，该队列是否自动删除，true自动删除
        //5.其它高级参数
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //信道发消息
        String message = "hello world";
        //参数：1.发送到哪一个交换机（本次没有考虑）；2.路由的key值，本次是队列名称；3.其它参数信息；4.发送消息的消息体
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕！");
    }
}
