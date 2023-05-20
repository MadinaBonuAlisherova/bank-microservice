package com.javatech.finance.service;


import com.javatech.finance.kafka.BankAccountProducer;
import com.javatech.finance.repository.BankAccountRemoteRepository;
import com.javatech.finance.repository.BankAccountRepository;
import com.javatech.finance.repository.UtilityAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.common.model.dto.UtilityAccount;
import com.javatech.finance.common.model.entity.BankAccountEntity;
import com.javatech.finance.common.model.entity.UtilityAccountEntity;
import com.javatech.finance.common.model.mapper.BankAccountMapper;
import com.javatech.finance.common.model.mapper.UtilityAccountMapper;


@Service
@RequiredArgsConstructor
public class AccountService {

    private final BankAccountRemoteRepository bankAccountRemoteRepository;
    private BankAccountMapper bankAccountMapper = new BankAccountMapper();
    private UtilityAccountMapper utilityAccountMapper = new UtilityAccountMapper();
    private final BankAccountProducer bankAccountProducer;

    private final BankAccountRepository bankAccountRepository;
    private final UtilityAccountRepository utilityAccountRepository;

    public BankAccount readBankAccount(String accountNumber) {
        BankAccountEntity entity = bankAccountRepository.findByNumber(accountNumber).orElseThrow(EntityNotFoundException::new);
        return bankAccountMapper.convertToDto(entity);
    }

    public UtilityAccount readUtilityAccount(String provider) {
        UtilityAccountEntity utilityAccountEntity = utilityAccountRepository.findByProviderName(provider).orElseThrow(EntityNotFoundException::new);
        return utilityAccountMapper.convertToDto(utilityAccountEntity);
    }

    public UtilityAccount readUtilityAccount(Long id){
        return utilityAccountMapper.convertToDto(utilityAccountRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public BankAccount readRemoteBankAccount(String accountNumber) {
        return bankAccountRemoteRepository.getAccount(accountNumber);
    }

    public BankAccount readKafkaBankAccount(String accountNumber) {

        return bankAccountProducer.send(accountNumber);
    }
}