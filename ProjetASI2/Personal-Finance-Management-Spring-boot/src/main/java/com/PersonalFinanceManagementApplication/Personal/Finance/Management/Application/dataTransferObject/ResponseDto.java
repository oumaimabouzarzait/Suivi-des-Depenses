package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String statusCode;
    private String statusMissage;
}
