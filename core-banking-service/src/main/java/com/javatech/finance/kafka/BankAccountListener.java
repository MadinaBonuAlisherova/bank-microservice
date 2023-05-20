package com.javatech.finance.kafka;

import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BankAccountListener {

    private final AccountService bankAccountService;

    @KafkaListener(topics = "${kafka.topics.bank-account-topic.name}")  //it listens  what topics to read
    @SendTo
    public BankAccount listenBankAccount(String message) {
        log.info("Message was received! ");

        return bankAccountService.readBankAccount(message);
    }


}
