package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPersonDto {
    BigDecimal monthlyLimit;
    Long id_category;
    Long id_person;
}
