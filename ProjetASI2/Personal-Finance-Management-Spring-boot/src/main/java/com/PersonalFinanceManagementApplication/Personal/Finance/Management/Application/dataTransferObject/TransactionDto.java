package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    public TransactionDto(Long person_id, Long id_category, Long id_typePayment, BigDecimal amount) {
        this.person_id = person_id;
        this.id_category = id_category;
        this.id_typePayment = id_typePayment;
        this.amount = amount;
    }


    private Long person_id;

    private Long id_category;

    private Long id_typePayment;

    private BigDecimal amount;

    private String imageUrl;
}
