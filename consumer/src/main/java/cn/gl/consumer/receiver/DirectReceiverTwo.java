package cn.gl.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author guoliang
 * <p>
 * 多个监听绑定到同一个队列，会轮询对消息消费，不存在重复消费
 */
@Component
@RabbitListener(queues = "directQueue")
public class DirectReceiverTwo {
    @RabbitHandler
    public void process(Map message) {
        System.out.println("DirectReceiverTwo消费者收到消息  ：" + message.toString());
    }
}
