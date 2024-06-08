package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonUserDto {

    private User user;

    private Person person;

}
