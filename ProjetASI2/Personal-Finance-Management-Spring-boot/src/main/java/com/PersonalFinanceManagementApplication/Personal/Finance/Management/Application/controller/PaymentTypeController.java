package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.controller;


import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.PaymentType;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject.PaymentTypeDto;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/paymentType", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    @Autowired
    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @GetMapping("/getAllPaymentTypes")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<PaymentType>> getAllPaymentType(){
        List<PaymentType> paymentTypes = this.paymentTypeService.getAllPaymentTypes();
        return  ResponseEntity.status(HttpStatus.OK).body(paymentTypes);
    }

    @PostMapping("/addPayment")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<PaymentType> savePaymentType(@RequestBody PaymentTypeDto paymentTypeDto){
        PaymentType paymentType = PaymentTypeDto.mapToPaymentType(paymentTypeDto);
        paymentType = this.paymentTypeService.addPaymentType(paymentType);
        return   ResponseEntity.status(HttpStatus.CREATED).body(paymentType);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<PaymentType> updatePaymentTyp(@RequestBody PaymentTypeDto paymentTypeDto){
        PaymentType paymentType = this.paymentTypeService.UpdatePaymentType(paymentTypeDto.getName(), paymentTypeDto.getNewPaymentTypeName(),
                paymentTypeDto.getDescription());
        return  ResponseEntity.status(HttpStatus.CREATED).body(paymentType);
    }

}
