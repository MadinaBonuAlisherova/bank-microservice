package com.javatech.finance.service;

import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.common.model.dto.UtilityAccount;
import com.javatech.finance.common.model.entity.BankAccountEntity;
import com.javatech.finance.common.model.entity.UtilityAccountEntity;
import com.javatech.finance.common.model.mapper.BankAccountMapper;
import com.javatech.finance.common.model.mapper.UtilityAccountMapper;
import com.javatech.finance.exceptions.EntityNotFoundException;
import com.javatech.finance.repository.BankAccountRepository;
import com.javatech.finance.repository.UtilityAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class AccountService {

    private BankAccountMapper bankAccountMapper = new BankAccountMapper();
    private UtilityAccountMapper utilityAccountMapper = new UtilityAccountMapper();

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

    public String createAccount(String accountNumber) {

        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setNumber(accountNumber);
        bankAccountEntity.setActualBalance(new BigDecimal(100));

        return bankAccountMapper.convertToDto( bankAccountRepository.save(bankAccountEntity)).toString();

    }
}