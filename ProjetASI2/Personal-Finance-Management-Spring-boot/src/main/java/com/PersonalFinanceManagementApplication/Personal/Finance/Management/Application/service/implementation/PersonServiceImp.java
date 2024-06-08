package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.implementation;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.BudgetHistory;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.CategoryPerson;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.BudgetHistoryRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.CategoryPersonRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.PersonRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.PersonAlreadyExistsException;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.ResourceNotFoundException;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;
    private final BudgetHistoryRepository budgetHistoryRepository;

    private final CategoryPersonRepository  categoryPersonRepository;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository, BudgetHistoryRepository budgetHistoryRepository, CategoryPersonRepository categoryPersonRepository){
        this.personRepository = personRepository;
        this.budgetHistoryRepository = budgetHistoryRepository;
        this.categoryPersonRepository = categoryPersonRepository;
    }

    @Override
    public Person getPersonById(Long personId) {
        Person person = this.personRepository.findById(personId).orElseThrow(()->{
            throw new ResourceNotFoundException("Person", "id", personId.toString());
        });
        return person;
    }

    @Override
    public List<Person> getAllPersons() {
        return this.personRepository.findAll();
    }

    @Override
    public Person updatePersonInfo(Person person) {
        Long id = person.getId();
        Person personTemp = this.personRepository.findById(id).orElseThrow(()->{
            throw new PersonAlreadyExistsException("Person with id " + id + " already exists.");
        });

        BigDecimal oldTotalBudget = personTemp.getTotalBudget();

        personTemp.setFirstname(person.getFirstname() != null ? person.getFirstname() : personTemp.getFirstname());
        personTemp.setLastName(person.getLastName() != null ?  person.getLastName() : personTemp.getLastName());
        personTemp.setEmail(person.getEmail() != null ? person.getEmail() : personTemp.getEmail());

        if(oldTotalBudget.compareTo(person.getTotalBudget()) != 0){
            if (person.getTotalBudget().compareTo(BigDecimal.ZERO) < 0) {
                throw  new RuntimeException("value of Total Budget should be greater the Zero");
            }else{
                personTemp.setTotalBudget(person.getTotalBudget());
                personTemp.setTotalBudgetSpend(BigDecimal.ZERO);
                personTemp.setCurrenttotalBudget(person.getTotalBudget());
                // when a person update the Total Budge we need to set all the category Persons total Spent to zero
                List<CategoryPerson> categoryPersonList = this.categoryPersonRepository.findAllByPerson_Id(personTemp.getId());
                System.out.println("=====");
                System.out.println(categoryPersonList);
                System.out.println("=====");
                for (CategoryPerson categoryPerson:categoryPersonList) {
                    System.out.println(categoryPerson);
                    categoryPerson.setTotalSpent(BigDecimal.ZERO);
                    this.categoryPersonRepository.save(categoryPerson);
                }

                // new we need to add the previews Total to the Budget history;
               this.budgetHistoryRepository.save(new BudgetHistory(personTemp,personTemp.getTotalBudget() ,oldTotalBudget ,person.getTotalBudget(),LocalDateTime.now()));
            }
        }
        return this.personRepository.save(personTemp);
    }

}
