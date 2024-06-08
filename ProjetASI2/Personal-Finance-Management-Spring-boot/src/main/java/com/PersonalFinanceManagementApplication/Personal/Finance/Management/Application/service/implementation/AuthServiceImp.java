package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.implementation;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.User;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.PersonRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.UsersRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.PersonAlreadyExistsException;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.ResourceNotFoundException;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AuthServiceImp implements AuthService {

    private final UsersRepository usersRepository;
    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImp(UsersRepository usersRepository,PersonRepository personRepository, PasswordEncoder passwordEncoder){
        this.personRepository = personRepository;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User Login(User user) {
        String email = user.getEmail();

        User userTemp =this.usersRepository.findByEmail(email).orElseThrow(()->{
            throw new ResourceNotFoundException("User", "Email", email);
        });

        // Check if provided password matches encoded password
        if (passwordEncoder.matches(user.getPassword(), userTemp.getPassword())) {
            // Passwords match, authentication successful
            return userTemp;
        } else {
            // Passwords don't match, authentication failed
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    @Transactional
    public User Register(User user) {
        // First, check if a person with the same email exists
        String email = user.getEmail();
        this.personRepository.findByEmail(email).ifPresent(existingPerson -> {
            throw new PersonAlreadyExistsException("Person with email " + email + " already exists.");
        });

        // Save the person info in the database
        Person person = user.getPerson();
        System.out.println("====".repeat(20));
        System.out.println(person);
        System.out.println("====".repeat(20));
        person = this.personRepository.save(person);

        // Encrypt the password before saving the user
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Save the user data
        user = this.usersRepository.save(user);

        return user;
    }



    @Override
    public void logOut(User user) {

    }
}
