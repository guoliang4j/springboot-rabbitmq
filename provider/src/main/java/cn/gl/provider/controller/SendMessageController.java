package cn.gl.provider.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author guoliang
 */
@RestController
public class SendMessageController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        //消息携带绑定路由键 direcrRoutingKey 发送到交换机 directExchange
        rabbitTemplate.convertAndSend("directExchange", "direcrRoutingKey", getMap());
        return "ok";
    }

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        //消息携带绑定路由键 topic.man 发送到交换机 topicExchange
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", getMap());
        return "ok";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        //消息携带绑定路由键 topic.woman 发送到交换机 topicExchange
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", getMap());
        return "ok";
    }


    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        //消息不需要携带绑定路由键，带了也没用 FanoutExChange 扇形交换机
        rabbitTemplate.convertAndSend("fanoutExchange", null, getMap());
        return "ok";
    }

    /**
     * 生产者推送消息的消息确认
     * ①消息推送到server，但是在server里找不到交换机 -》触发ConfirmCallback()
     * ②消息推送到server，找到交换机了，但是没找到队列 -》ConfirmCallback()和ReturnsCallback()都会触发
     * ③消息推送到sever，交换机和队列啥都没找到 -》触发ConfirmCallback()
     * ④消息推送成功 -》 只会触发ConfirmCallback()
     */
    @GetMapping("/testMessageAck")
    public String testMessageAck() {
        //把消息推送到一个不存在的交换机中，只会触发ConfirmCallback()
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", getMap());
        return "ok";
    }

    @GetMapping("/testMessageAck2")
    public String testMessageAck2() {
        //把消息推送到一个没有任何队列的交换机中，ConfirmCallback()和ReturnsCallback()都会触发
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", getMap());
        return "ok";
    }

    public Map<String, Object> getMap() {
        UUID uuid = UUID.randomUUID();
        String messageId = uuid.toString();
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>(10);
        map.put("messageId", messageId);
        String messageData = "test message,hello!";
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        return map;
    }
}
