package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.implementation;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.PaymentType;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.PaymentTypeRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.ResourceNotFoundException;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeServiceImp implements PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;

    @Autowired
    public PaymentTypeServiceImp(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @Override
    public PaymentType addPaymentType(PaymentType paymentType) {
        this.paymentTypeRepository.findByName(paymentType.getName()).ifPresent(presentCategory ->{
            throw new RuntimeException("PaymentType already  exists.");
        });
        return this.paymentTypeRepository.save(paymentType);
    }

    @Override
    public PaymentType UpdatePaymentType(String paymentTypeName, String newPaymentTypeName,String description) {
        PaymentType paymentType = this.paymentTypeRepository.findByName(paymentTypeName).orElseThrow(()->{
            throw new ResourceNotFoundException("Payment Type", "name ", paymentTypeName);
        });
        paymentType.setName(newPaymentTypeName);
        paymentType.setDescription(description);
        return this.paymentTypeRepository.save(paymentType);
    }

    @Override
    public List<PaymentType> getAllPaymentTypes() {
        return this.paymentTypeRepository.findAll();
    }
}
