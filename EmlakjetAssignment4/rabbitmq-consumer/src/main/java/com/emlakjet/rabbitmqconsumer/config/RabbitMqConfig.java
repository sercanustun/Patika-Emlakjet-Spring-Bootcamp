package com.emlakjet.rabbitmqconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Config class

@Configuration
public class RabbitMqConfig {

        @Value("${queue.name}")
        private String queueName;

        @Value("${spring.rabbitmq.template.exchange}")
        private String exchangeName;

        @Value("${spring.rabbitmq.template.routing-key}")
        private String routingName;

        @Bean
        Queue queue(){
            return new Queue(queueName, false);
        }

        @Bean
        DirectExchange directExchange(){
            return new DirectExchange(exchangeName);
        }

        @Bean
        Binding binding(Queue queue, DirectExchange directExchange){
            return BindingBuilder.bind(queue).to(directExchange).with(routingName);
        }

        @Bean
        MessageConverter messageConverter(){
            return new Jackson2JsonMessageConverter();
        }

        @Bean
        RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(messageConverter());
            return rabbitTemplate;
        }
}