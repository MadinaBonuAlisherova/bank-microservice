package java.com.javatech.common.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.com.javatech.common.enums.AccountStatus;
import java.com.javatech.common.enums.AccountType;
import java.com.javatech.common.model.entity.UserEntity;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "banking_core_account")
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private BigDecimal availableBalance;

    private BigDecimal actualBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}