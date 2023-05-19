package com.javatech.finance.controller;


import com.javatech.finance.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatech.finance.common.controller.AccountControllerInterface;
import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.common.model.dto.UtilityAccount;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/account")
@RequiredArgsConstructor
class AccountController implements AccountControllerInterface {

    private final AccountService accountService;

    public ResponseEntity<BankAccount> getBankAccount(@PathVariable("account_number") String accountNumber) {
        log.info("Reading account by ID {}", accountNumber);
        return ResponseEntity.ok(accountService.readBankAccount(accountNumber));
    }

    public ResponseEntity<UtilityAccount> getUtilityAccount(@PathVariable("account_name") String providerName) {
        log.info("Reading utitlity account by ID {}", providerName);
        return ResponseEntity.ok(accountService.readUtilityAccount(providerName));
    }

    @PostMapping(value = "/{account_number}")
    public ResponseEntity<String> createAccount(@PathVariable("account_number") String accountNumber){
        return ResponseEntity.ok(accountService.createAccount(accountNumber));
    }

}