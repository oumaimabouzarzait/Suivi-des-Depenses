package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.PaymentType;

import java.util.List;

public interface PaymentTypeService {
    public PaymentType addPaymentType(PaymentType paymentType);

    public  PaymentType UpdatePaymentType(String paymentTypeName, String newPaymentTypeName, String description);

    public List<PaymentType> getAllPaymentTypes();
}
