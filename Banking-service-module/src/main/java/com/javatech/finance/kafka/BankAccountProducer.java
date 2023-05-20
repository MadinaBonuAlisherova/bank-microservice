package com.javatech.finance.kafka;

import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.config.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.requestreply.RequestReplyTypedMessageFuture;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class BankAccountProducer {

    private final ReplyingKafkaTemplate<String, String, BankAccount> replyingKafkaBankAccountTemplate;
    private final KafkaProperties kafkaProperties;


    public BankAccount send(String accountNumber) {
        RequestReplyTypedMessageFuture<String, String, BankAccount> bankAccountRequestReplyTypedMessageFuture
                = sendMessage(accountNumber);
        try {
            Message<BankAccount> bankAccountMessage = bankAccountRequestReplyTypedMessageFuture.get();
            return bankAccountMessage.getPayload();

        } catch (InterruptedException | ExecutionException exception) {
            log.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    private RequestReplyTypedMessageFuture<String, String, BankAccount> sendMessage(String accountNumber){
        Message<String> message = MessageBuilder.withPayload(accountNumber)
                .setHeader(KafkaHeaders.TOPIC, getTopic().getName())
                .setHeader(KafkaHeaders.REPLY_TOPIC, getTopic().getReplyName())  //in request we have topic and reply topic name, so that listener, doest have to look for to which topic it replies
                .build();
        return replyingKafkaBankAccountTemplate.sendAndReceive(message, ParameterizedTypeReference.forType(BankAccount.class));
    }

    private KafkaProperties.Topic getTopic(){
        return kafkaProperties.getTopic(KafkaProperties.BANK_ACCOUNT_TOPIC);
    }
}
