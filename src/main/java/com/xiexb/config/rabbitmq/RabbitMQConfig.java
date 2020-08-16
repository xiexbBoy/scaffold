package com.xiexb.config.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.sun.istack.internal.NotNull;
import com.xiexb.config.constant.RabbitMQConstant;
import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMQ 配置
 * @author xiexiaobo
 */
@Data
@Configuration
@ConfigurationProperties(value = "spring.rabbitmq")
public class RabbitMQConfig {

    @NotNull
    private String host;

    @NotNull
    private Integer port;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Bean
    public ConnectionFactory collectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);

        return connectionFactory;
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(RabbitMQConstant.PROCESS_EXCHANGE).build();
    }

    @Bean
    public Exchange delayExchange() {
        return ExchangeBuilder.directExchange(RabbitMQConstant.DELAY_EXCHANGE).build();
    }

    @Bean
    public Queue delayQueue() {
        return QueueBuilder
                .durable(RabbitMQConstant.DELAY_QUEUE)
                .deadLetterExchange(RabbitMQConstant.PROCESS_EXCHANGE)
                .deadLetterRoutingKey(RabbitMQConstant.PROCESS_ROUTING_KEY)
                .build();
    }

    @Bean
    public Binding delayBinding() {
        return BindingBuilder
                .bind(delayQueue())
                .to(delayExchange())
                .with(RabbitMQConstant.DELAY_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder
                .durable(RabbitMQConstant.PROCESS_QUEUE)
                .build();
    }

    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(RabbitMQConstant.PROCESS_ROUTING_KEY)
                .noargs();
    }
}
