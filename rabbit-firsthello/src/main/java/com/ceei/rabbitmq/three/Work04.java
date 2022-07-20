package com.ceei.rabbitmq.three;

import com.ceei.rabbitmq.utils.RabbitMqUtils;
import com.ceei.rabbitmq.utils.SleepUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @ClassName: Work03
 * @Author: dqdgo
 * @Description: 验证消息在手动应答时不丢失、放回队列重新消费
 * @Create: 2022-07-20 20:13
 **/
public class Work04 {
    //队列名称
    public static final String Task_QUEUE_NAME="ack_queue";
    //接收消息
    public static void main(String[] args) throws Exception {
        Channel chanel = RabbitMqUtils.getChanel();
        System.out.println("C2等待接收消息处理-时间长");
        //采用手动应答
        boolean autoAck =false;
        //lambda表达式声明 接收消息
        DeliverCallback deliverCallback = (consumerTag, message)->{
            //沉睡1秒
            SleepUtils.sleep(30);
            System.out.println("接收到的消息："+new String(message.getBody(),"UTF-8"));
            //手动应答
            /**
             * 1.消息的标记 Tag
             * 2.是否批量应答
             */
            chanel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };
        //取消消息时的回调
        CancelCallback cancelCallback = consumerTag->{
            System.out.println(consumerTag+"消息者取消消费接口回调逻辑");
        };
        chanel.basicConsume(Task_QUEUE_NAME,autoAck,deliverCallback,cancelCallback);


    }
}
