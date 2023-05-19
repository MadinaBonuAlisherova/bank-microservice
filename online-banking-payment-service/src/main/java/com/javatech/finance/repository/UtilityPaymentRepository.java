package com.javatech.finance.repository;


import com.javatech.finance.model.dto.UtilityPayment;
import com.javatech.finance.model.entity.UtilityPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilityPaymentRepository extends JpaRepository<UtilityPaymentEntity, UtilityPayment> {
}