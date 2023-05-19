package com.javatech.finance.repository.client;


import com.javatech.finance.common.controller.AccountControllerInterface;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "bank-account-client", url = "localhost:8081/api/v1/account")
public interface BankAccountClient extends AccountControllerInterface {

}
