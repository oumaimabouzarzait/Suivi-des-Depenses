package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Mapper;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.User;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject.UserDto;

public class UserMapper {

    private UserMapper(){};

    public static User mapToUser(UserDto userdto){
        User user = new User();
        user.setPerson(userdto.getPerson());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setRole(userdto.getRole());
        return  user;
    }

    public static UserDto mapToUserDto(User user){
        return  new UserDto(user);
    }
}
