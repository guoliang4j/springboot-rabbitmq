package cn.gl.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author guoliang
 */
@Component
@RabbitListener(queues = "directQueue")
public class DirectReceiver {
    @RabbitHandler
    public void process(Map message) {
        System.out.println("DirectReceiver消费者收到消息  ：" + message.toString());
    }
}
