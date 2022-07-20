package com.ceei.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @ClassName: RabbitMqUtils
 * @Author: dqdgo
 * @Description:此类为连接工厂创建信道的工具类
 * @Create: 2022-07-19 15:45
 **/
public class RabbitMqUtils {
    public static Channel getChanel() throws Exception{
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置工厂IP、用户名、密码 连接RabbitMQ队列
        factory.setHost("192.168.6.158");
        factory.setUsername("admin");
        factory.setPassword("123");
        //创建连接
        Connection connection = factory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();
        return channel;
    }
}
