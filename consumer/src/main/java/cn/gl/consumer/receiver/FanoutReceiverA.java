package cn.gl.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author gl
 * @Date 2022/1/29/0029 12:55
 * @Description
 */
@Component
@RabbitListener(queues = "queueA")
public class FanoutReceiverA {

    @RabbitHandler
    public void process(Map message) {
        System.out.println("FanoutReceiverA消费者收到消息 ： " + message.toString());
    }
}
