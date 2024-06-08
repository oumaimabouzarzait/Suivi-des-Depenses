package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.User;



public interface AuthService {

    public User Login(User user) ;
    public User Register(User user);
    public void logOut(User user);
}
