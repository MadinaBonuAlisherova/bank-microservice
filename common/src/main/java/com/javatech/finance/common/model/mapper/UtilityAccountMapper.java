package com.javatech.finance.common.model.mapper;

import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.common.model.dto.UtilityAccount;
import org.springframework.beans.BeanUtils;

import com.javatech.finance.common.model.entity.BankAccountEntity;
import com.javatech.finance.common.model.entity.UtilityAccountEntity;
import java.util.List;

public class UtilityAccountMapper extends BaseMapper<UtilityAccountEntity, UtilityAccount> {
    @Override
    public UtilityAccountEntity convertToEntity(UtilityAccount dto, Object... args) {
        UtilityAccountEntity entity = new UtilityAccountEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }

    @Override
    public UtilityAccount convertToDto(UtilityAccountEntity entity, Object... args) {
        UtilityAccount dto = new UtilityAccount();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }

    @Override
    protected List<BankAccount> convertToDto(List<BankAccountEntity> accounts) {
        return null;
    }
}
