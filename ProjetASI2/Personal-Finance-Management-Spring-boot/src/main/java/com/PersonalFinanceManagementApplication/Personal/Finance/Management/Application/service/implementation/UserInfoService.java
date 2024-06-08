package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.implementation;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.User;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.UserInfoDetails;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.UsersRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userDetail = usersRepository.findByEmail(email);
        return userDetail.map(UserInfoDetails::new).orElseThrow(()->{
            throw new ResourceNotFoundException("User", "email", email);
        });
    }

}
