package com.xiexb.config.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import com.xiexb.config.constant.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xiexiaobo
 */
@Slf4j
@Component
public class ProcessQueueListener {

    @RabbitHandler
    @RabbitListener(queues = {RabbitMQConstant.PROCESS_QUEUE})
    private void processHandler(Channel channel, Message message) {
        //TODO
    }
}
