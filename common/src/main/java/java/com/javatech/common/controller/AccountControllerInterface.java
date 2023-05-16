package java.com.javatech.common.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.com.javatech.common.model.dto.BankAccount;
import java.com.javatech.common.model.dto.UtilityAccount;

public interface AccountControllerInterface {

    @GetMapping("/bank-account/{account_number}")
    ResponseEntity<BankAccount> getBankAccount(@PathVariable("account_number") String accountNumber);

    @GetMapping("/util-account/{account_name}")
    ResponseEntity<UtilityAccount> getUtilityAccount(@PathVariable("account_name") String providerName);


    }
