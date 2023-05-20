package com.javatech.finance.config;

import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.config.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Collection;

import static com.javatech.finance.config.properties.KafkaProperties.BANK_ACCOUNT_TOPIC;

@Configuration
@RequiredArgsConstructor
@EnableKafka
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    //both banking-service and core service can receive and sent request and response through kafka

    //kafka is async, so methods are sync

    @Bean //serialiazbale and deserilization for ibjects
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    private <K, V> ConcurrentMessageListenerContainer<K, V> replyContainer(
            ConcurrentKafkaListenerContainerFactory<K, V> containerFactory,
            String name
    ) {
        ConcurrentMessageListenerContainer<K, V> replyContainer = containerFactory.createContainer(name);
        replyContainer.setAutoStartup(true);
        return replyContainer;
    }

}
