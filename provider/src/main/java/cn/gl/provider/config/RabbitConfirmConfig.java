package cn.gl.provider.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author gl
 * @Date 2022/1/29/0029 13:44
 * @Description rabbitmq消息确认 回调函数
 */
@Configuration
public class RabbitConfirmConfig {

    @Bean
    public RabbitTemplate creatRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置Mandatory 强制性回调
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
                System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
                System.out.println("ConfirmCallback:     " + "原因：" + cause);
            }
        });
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {

            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("ReturnCallback:     " + "消息：" + returnedMessage.getMessage());
                System.out.println("ReturnCallback:     " + "回应码：" + returnedMessage.getReplyCode());
                System.out.println("ReturnCallback:     " + "回应信息：" + returnedMessage.getReplyText());
                System.out.println("ReturnCallback:     " + "交换机：" + returnedMessage.getExchange());
                System.out.println("ReturnCallback:     " + "路由键：" + returnedMessage.getRoutingKey());
            }
        });
        return rabbitTemplate;
    }
}
