package com.javatech.finance.common.model.mapper;


import com.javatech.finance.common.model.dto.BankAccount;
import org.springframework.beans.BeanUtils;

import com.javatech.finance.common.model.entity.BankAccountEntity;
import java.util.List;

public class BankAccountMapper extends BaseMapper<BankAccountEntity, BankAccount> {

    @Override
    public BankAccountEntity convertToEntity(BankAccount dto, Object... args) {
        BankAccountEntity entity = new BankAccountEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity, "user");
        }
        return entity;
    }

    @Override
    public BankAccount convertToDto(BankAccountEntity entity, Object... args) {
        BankAccount dto = new BankAccount();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto, "user");
        }
        return dto;
    }

    @Override
    protected List<BankAccount> convertToDto(List<BankAccountEntity> accounts) {
        return null;
    }


}
