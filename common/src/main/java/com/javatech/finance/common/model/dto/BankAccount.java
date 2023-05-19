package com.javatech.finance.common.model.dto;

import lombok.Data;

import com.javatech.finance.common.enums.AccountStatus;
import com.javatech.finance.common.enums.AccountType;
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
