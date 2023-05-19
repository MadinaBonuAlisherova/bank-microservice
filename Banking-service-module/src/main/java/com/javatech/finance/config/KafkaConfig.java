package com.javatech.finance.config;

import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.config.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
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

    private final GenericWebApplicationContext genericWebApplicationContext;
    private final KafkaProperties kafkaProperties;


    @Bean
    public NewTopic bankAccountTopic() {
        KafkaProperties.Topic topicConfig = kafkaProperties.getTopics().get(BANK_ACCOUNT_TOPIC);
        return new NewTopic(topicConfig.getName(), topicConfig.getPartitions(), (short) topicConfig.getReplicationFactor());
    }

    @Bean
    public ReplyingKafkaTemplate<String, String, BankAccount> replyingKafkaBankAccountTemplate(ProducerFactory<String, String> pf, ConcurrentKafkaListenerContainerFactory<String, BankAccount> cf) {
        KafkaProperties.Topic topic = kafkaProperties.getTopic(BANK_ACCOUNT_TOPIC);
        ReplyingKafkaTemplate<String, String, BankAccount> template = new ReplyingKafkaTemplate<>(pf, replyContainer(cf, topic.getReplyName()));
        template.setMessageConverter(converter());
        return template;
    }

    @Bean //serialiazbale and deserilization for ibjects
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @PostConstruct //after bean creation of init, bean will be created inw application
    public void createTopics() {
        Collection<KafkaProperties.Topic> topics = kafkaProperties.getTopics().values();
        createTopic(topics);
        createReplyTopic(topics);
        createReplyDltTopic(topics);
    }

     private void registerTopic(KafkaProperties.Topic topic, String name){
         genericWebApplicationContext.registerBean(name, NewTopic.class, () -> topic.toNewTopic(name));
     }

    private void createTopic(Collection<KafkaProperties.Topic> topics) {
        topics.forEach(topic -> registerTopic(topic, topic.getName()));
    }

    private void createReplyTopic(Collection<KafkaProperties.Topic> topics) {
        topics.forEach(topic -> registerTopic(topic, topic.getReplyName()));
    }

    private void createReplyDltTopic(Collection<KafkaProperties.Topic> topics) {
        topics.forEach(topic -> registerTopic(topic, topic.getDltName()));
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
