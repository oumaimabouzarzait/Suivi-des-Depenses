package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;

import java.util.List;

public interface PersonService {

    public Person getPersonById(Long personId);

    public List<Person> getAllPersons();
    public Person updatePersonInfo(Person person);
//    public Person saveOrUpdatetotalBudget(Person person);

}
