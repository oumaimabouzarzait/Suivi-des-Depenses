package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    private static final String defualtRole = "ROLE_USER";

    private String email;
    private String password;
    private Person person;
    private String role;

    public UserDto(String email, String password,String role){
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public UserDto(String email, String password){
        this.email = email;
        this.password = password;
        this.role = defualtRole;
    }

    public UserDto(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.person = user.getPerson();
        this.role = user.getRole();
    }


}
