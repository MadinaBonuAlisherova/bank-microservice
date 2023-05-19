//package com.javatech.finance.service;
//
//import com.javatech.finance.model.TransactionStatus;
//import com.javatech.finance.model.dto.UtilityPayment;
//import com.javatech.finance.model.entity.UtilityPaymentEntity;
//import com.javatech.finance.model.mapper.UtilityPaymentMapper;
//import com.javatech.finance.model.rest.request.UtilityPaymentRequest;
//import com.javatech.finance.model.rest.response.UtilityPaymentResponse;
//import com.javatech.finance.repository.UtilityPaymentRepository;
//import com.javatech.finance.service.rest.BankingCoreRestClient;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class UtilityPaymentService {
//    private final UtilityPaymentRepository utilityPaymentRepository;
//    private final BankingCoreRestClient bankingCoreRestClient;
//
//    private UtilityPaymentMapper utilityPaymentMapper = new UtilityPaymentMapper();
//
//    public UtilityPaymentResponse utilPayment(Util
//                                                      ityPaymentRequest paymentRequest) {
//        log.info("Utility payment processing {}", paymentRequest.toString());
//
//        UtilityPaymentEntity entity = new UtilityPaymentEntity();
//        BeanUtils.copyProperties(paymentRequest, entity);
//        entity.setStatus(TransactionStatus.PROCESSING);
//        UtilityPaymentEntity optUtilPayment = utilityPaymentRepository.save(entity);
//
//        UtilityPaymentResponse utilityPaymentResponse = bankingCoreRestClient.utilityPayment(paymentRequest);
//        log.info("Transaction response {}", utilityPaymentResponse.toString());
//
//        optUtilPayment.setStatus(TransactionStatus.SUCCESS);
//        optUtilPayment.setTransactionId(utilityPaymentResponse.getTransactionId());
//        utilityPaymentRepository.save(optUtilPayment);
//
//        return UtilityPaymentResponse.builder().message("Utility Payment Successfully Processed").transactionId(utilityPaymentResponse.getTransactionId()).build();
//    }
//
//    public List<UtilityPayment> readPayments(Pageable pageable) {
//        Page<UtilityPaymentEntity> allUtilPayments = utilityPaymentRepository.findAll(pageable);
//        return utilityPaymentMapper.convertToDtoList(allUtilPayments.getContent());
//    }
//}