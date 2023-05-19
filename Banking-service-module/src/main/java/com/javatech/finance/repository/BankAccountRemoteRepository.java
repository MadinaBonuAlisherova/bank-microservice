package com.javatech.finance.repository;

import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.repository.client.BankAccountClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BankAccountRemoteRepository {

    private final BankAccountClient bankAccountClient;

    public BankAccount getAccount(String accountNumber) {
        return  bankAccountClient.getBankAccount(accountNumber).getBody();
    }
}
