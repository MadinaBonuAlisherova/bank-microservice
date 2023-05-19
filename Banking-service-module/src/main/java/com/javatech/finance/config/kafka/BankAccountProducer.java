package com.javatech.finance.config.kafka;

import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.config.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankAccountProducer {

    private final ReplyingKafkaTemplate<String, String, BankAccount> replyingKafkaBankAccountTemplate;
    private final KafkaProperties kafkaProperties;


}
