package com.mah312.amqp.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@AllArgsConstructor
@Configuration
public class AMQPConfig {

    private final ConnectionFactory connectionFactory;

    @Bean
    @Primary
    public AmqpTemplate getAmqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory getSimpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory rabbitListener = new SimpleRabbitListenerContainerFactory();
        rabbitListener.setConnectionFactory(connectionFactory);
        rabbitListener.setMessageConverter(getMessageConverter());
        return rabbitListener;
    }

    @Bean
    public MessageConverter getMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
