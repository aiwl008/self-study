package com.aiwl.pms.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 消息生成者
 */
public class Producer {
    public final static String QUEUE_NAME="rabbitMQ.test";

    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("192.168.2.78");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setPort(5672);
        factory.setConnectionTimeout(10000); 
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //  声明一个队列        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello RabbitMQ";
        //发送消息到队列中
//        for(int i=0;i<100;i++){
//        	Thread.sleep(1000);
        	channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
//        }
        System.out.println("Producer Send +'" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}