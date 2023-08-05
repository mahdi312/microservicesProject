package com.mah312.amqp.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(Object message, String exchange, String routingKey) {
        log.info("publishing {} to exchange: {} by key: {}", message, exchange, routingKey);
        amqpTemplate.convertAndSend(exchange, routingKey, message);
        log.info(" ***======%%%===> published <=====%%%====*** ");
    }


}
