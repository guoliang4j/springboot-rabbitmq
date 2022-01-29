package cn.gl.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guoliang
 */
@Configuration
public class DirectExchangeConfig {

    @Bean
    public Queue directQueue() {
        return new Queue("directQueue", true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange", true, false);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direcrRoutingKey");
    }

    @Bean
    public DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }
}
