package cn.gl.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guoliang
 */
@Configuration
public class TopicExchangeConfig {
    private static final String man = "topic.man";
    private static final String woman = "topic.woman";

    @Bean
    Queue firstQueue() {
        return new Queue(man);
    }

    @Bean
    Queue secondQueue() {
        return new Queue(woman);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding binding1() {
        return BindingBuilder.bind(firstQueue()).to(topicExchange()).with(man);
    }

    @Bean
        //消息携带的路由键只要以topic.开头，都会发到secondQueue队列中
    Binding binding2() {
        return BindingBuilder.bind(secondQueue()).to(topicExchange()).with("topic.#");
    }
}
