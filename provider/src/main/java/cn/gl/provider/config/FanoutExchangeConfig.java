package cn.gl.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guoliang
 */
@Configuration
public class FanoutExchangeConfig {
    @Bean
    public Queue queueA() {
        return new Queue("queueA", true);
    }

    @Bean
    public Queue queueB() {
        return new Queue("queueB", true);
    }

    @Bean
    public Queue queueC() {
        return new Queue("queueC", true);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    Binding bindingC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }
}
