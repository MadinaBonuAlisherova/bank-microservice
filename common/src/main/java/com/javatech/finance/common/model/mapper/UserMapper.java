package com.javatech.finance.common.model.mapper;


import com.javatech.finance.common.model.dto.BankAccount;
import org.springframework.beans.BeanUtils;

import com.javatech.finance.common.model.dto.User;
import com.javatech.finance.common.model.entity.BankAccountEntity;
import com.javatech.finance.common.model.entity.UserEntity;
import java.util.List;

public class UserMapper extends BaseMapper<UserEntity, User> {
    private BankAccountMapper bankAccountMapper = new BankAccountMapper();

    @Override
    public UserEntity convertToEntity(User dto, Object... args) {
        UserEntity entity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity, "accounts");
            entity.setAccounts(bankAccountMapper.convertToEntityList(dto.getBankAccounts()));
        }
        return entity;
    }

    @Override
    public User convertToDto(UserEntity entity, Object... args) {
        User dto = new User();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto, "accounts");
            dto.setBankAccounts(bankAccountMapper.convertToDto(entity.getAccounts()));
        }
        return dto;
    }

    @Override
    protected List<BankAccount> convertToDto(List<BankAccountEntity> accounts) {
        return null;
    }
}
