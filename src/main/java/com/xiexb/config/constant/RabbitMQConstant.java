package com.xiexb.config.constant;

/**
 *
 * @author xiexiaobo
 */
public class RabbitMQConstant {

    /**
     * 死信交换器
     */
    public static final String DELAY_EXCHANGE = "delay_exchange";

    /**
     * 普通交换器
     */
    public static final String PROCESS_EXCHANGE = "process_exchange";

    /**
     * 死信队列
     */
    public static final String DELAY_QUEUE= "delay_queue";
    public static final String DELAY_ROUTING_KEY = "delay_routing_key";

    /**
     * 普通队列
     */
    public static final String PROCESS_QUEUE = "process_queue";
    public static final String PROCESS_ROUTING_KEY = "process_routing_key";
}
