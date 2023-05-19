package com.javatech.finance.config.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties("kafka")
@Data
public class KafkaProperties {

    public final static  String BANK_ACCOUNT_TOPIC = "bank-account-topic";
    private Map<String, Topic> topics;

    public Topic getTopic(String bankAccountTopic) {
        return topics.get(bankAccountTopic);
    }

    //take by name by topic param in config class/ create topics on on kafak brokers in kafka config class

    @Data
    public static class Topic {
        private static final String REPLY_POSTFIX = ".REPLY";
        private static final String DLT_POSTFIX = ".DLT"; //dead letter topic - used to check if reply is normal return reply topic
                                                          // or reply+error/exceptions
        private String name;
        private int replicationFactor;
        private int partitions;

        public NewTopic toNewTopic(String name) {
            return TopicBuilder.name(name).partitions(partitions).replicas(replicationFactor).build();
        }

        public String getReplyName(){
            return name+REPLY_POSTFIX;
        }

        public String getDltName(){
            return name+DLT_POSTFIX;
        }
    }


}
