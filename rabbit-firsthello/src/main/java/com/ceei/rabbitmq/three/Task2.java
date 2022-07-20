package com.ceei.rabbitmq.three;

import com.ceei.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * @ClassName: Task2
 * @Author: dqdgo
 * @Description: 验证消息在手动应答时不丢失、放回队列重新消费
 * @Create: 2022-07-20 14:54
 **/
public class Task2 {
    //队列名称
    public static final String Task_QUEUE_NAME="ack_queue";

    public static void main(String[] args) throws Exception {
        Channel chanel = RabbitMqUtils.getChanel();
        //队列的声明
        chanel.queueDeclare(Task_QUEUE_NAME,false,false,false,null);
        //发送消息
        //从控制台当中接收消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            chanel.basicPublish("",Task_QUEUE_NAME,null,next.getBytes("UTF-8"));
            System.out.println("生产者发送消息："+next);
        }

    }



}
