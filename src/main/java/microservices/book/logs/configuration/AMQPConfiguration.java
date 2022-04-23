package microservices.book.logs.configuration;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AMQPConfiguration {

    @Bean
    TopicExchange topicExchange( @Value("${ampq.exchange.topic}") final String topic){
        return ExchangeBuilder.topicExchange(topic).durable(true).build();
    }

    @Bean
    Queue queue(@Value("${amqp.topic.queue}") final String  queue){
        return QueueBuilder.durable(queue).build();
    }

    @Bean
    Binding binding(final TopicExchange exchange, final Queue queue){
        return  BindingBuilder.bind(queue).to(exchange).with("#");
    }
}
