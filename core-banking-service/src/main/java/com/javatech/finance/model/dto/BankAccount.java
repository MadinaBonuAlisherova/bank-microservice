package com.javatech.finance.model.dto;

import com.javatech.finance.enums.AccountStatus;
import com.javatech.finance.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccount {

    private Long id;
    private String number;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal availableBalance;
    private BigDecimal actualBalance;
    private User user;

}
