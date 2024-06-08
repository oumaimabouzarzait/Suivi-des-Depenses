package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private User user;
    private String message;
}
