package java.com.javatech.finance.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.com.javatech.common.model.BankAccountEntity;
import java.com.javatech.common.model.UtilityAccountEntity;
import java.com.javatech.common.model.dto.BankAccount;
import java.com.javatech.common.model.dto.UtilityAccount;
import java.com.javatech.finance.model.mapper.BankAccountMapper;
import java.com.javatech.finance.model.mapper.UtilityAccountMapper;
import java.com.javatech.finance.repository.BankAccountRepository;
import java.com.javatech.finance.repository.UtilityAccountRepository;


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

}