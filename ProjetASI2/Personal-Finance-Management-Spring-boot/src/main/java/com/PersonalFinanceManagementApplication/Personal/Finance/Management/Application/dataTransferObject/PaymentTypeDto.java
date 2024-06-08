package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Category;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTypeDto {

    private Long id;

    private String name;
    private String description;

    private String newPaymentTypeName;

    public PaymentTypeDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public PaymentTypeDto(String name, String description, String newPaymentTypeName) {
        this.name = name;
        this.description = description;
        this.newPaymentTypeName = newPaymentTypeName;
    }

    public static PaymentType mapToPaymentType(PaymentTypeDto paymentTypeDto){
        PaymentType paymentType = new PaymentType();
        paymentType.setName(paymentTypeDto.getName());
        paymentType.setId(paymentTypeDto.getId());
        paymentType.setDescription(paymentTypeDto.getDescription());
        return paymentType;
    }
}
