package com.ceei.rabbitmq.two;

import com.ceei.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * @ClassName: Task01
 * @Author: dqdgo
 * @Description: 生产者 发送大量消息
 * @Create: 2022-07-19 17:28
 **/
public class Task01 {
    //发送队列名称
    public static final String QUEUE_NAME="hello";

    //发送大量消息
    public static void main(String[] args) throws Exception {
        Channel chanel = RabbitMqUtils.getChanel();
        //队列的声明
        chanel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发送消息
        //从控制台当中接收消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            chanel.basicPublish("",QUEUE_NAME,null,next.getBytes());
            System.out.println("发送消息完成："+next);
        }
    }

}
