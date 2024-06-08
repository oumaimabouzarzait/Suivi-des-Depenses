package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

}
