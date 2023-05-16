package com.javatech.finance.controller;

import com.javatech.finance.model.dto.BankAccount;
import com.javatech.finance.model.dto.UtilityAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface AccountControllerInterface {

    @GetMapping("/bank-account/{account_number}")
    public ResponseEntity<BankAccount> getBankAccount(@PathVariable("account_number") String accountNumber);

    @GetMapping("/util-account/{account_name}")
    public ResponseEntity<UtilityAccount> getUtilityAccount(@PathVariable("account_name") String providerName);


    }
