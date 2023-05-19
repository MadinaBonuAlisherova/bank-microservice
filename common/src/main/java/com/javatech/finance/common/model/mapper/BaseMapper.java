package com.javatech.finance.common.model.mapper;

import com.javatech.finance.common.model.dto.BankAccount;
import com.javatech.finance.common.model.entity.BankAccountEntity;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseMapper<E, D> {
    public abstract E convertToEntity(D dto, Object... args);

    public abstract D convertToDto(E entity, Object... args);

    public List<E> convertToEntityList(Collection<D> dto, Object... args) {
        return dto.stream().map(d -> convertToEntity(d, args)).collect(Collectors.toList());
    }

    public List<D> convertToDtoList(Collection<E> entity, Object... args) {
        return entity.stream().map(e -> convertToDto(e, args)).collect(Collectors.toList());
    }

//    public List<BankAccountEntity> convertToEntityList(Collection<D> dto, Object... args) {
//        return convertToEntity(dto, args).stream().collect(Collectors.toList());
//    }

//    public List<BankAccount> convertToDtoList(Collection<BankAccount> entitys, Object... args) {
//        List<BankAccount> dtos = entitys.stream().map(e -> convertToDto())
//        return econvertToDto(entitys, args).stream().collect(Collectors.toList());
//    }

    public Set<E> convertToEntitySet(Collection<D> dto, Object... args) {
        return dto.stream().map(d -> convertToEntity(d, args)).collect(Collectors.toSet());
    }

    public Set<D> convertToDtoSet(Collection<E> entity, Object... args) {
        return entity.stream().map(d -> convertToDto((E) entity, args)).collect(Collectors.toSet());
    }

    protected abstract List<BankAccount> convertToDto(List<BankAccountEntity> accounts);
}
