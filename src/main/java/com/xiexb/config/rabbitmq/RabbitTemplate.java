package com.xiexb.config.rabbitmq;

import com.xiexb.config.constant.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author xiexiaobo
 */
@Slf4j
public class RabbitTemplate {

    @Resource
    private org.springframework.amqp.rabbit.core.RabbitTemplate rabbitTemplate;

    @Bean
    public void sendMessage(String routingKey, Object message) {
        rabbitTemplate.convertAndSend(RabbitMQConstant.PROCESS_ROUTING_KEY, routingKey, message);
    }

    @Bean
    public void sendDelayMessage(String routingKey, Object object, int ttl) {
        rabbitTemplate.convertAndSend(RabbitMQConstant.DELAY_EXCHANGE, routingKey, object, message -> {
            message.getMessageProperties().setExpiration(ttl + "");
            return message;
        });
    }
}
