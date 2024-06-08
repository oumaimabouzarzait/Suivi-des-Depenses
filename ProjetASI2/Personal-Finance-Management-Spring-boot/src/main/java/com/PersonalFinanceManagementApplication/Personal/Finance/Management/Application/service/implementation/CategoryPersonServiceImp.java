package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.implementation;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Category;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.CategoryPerson;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.CategoryPersonRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.CategoryRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.PersonRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.CategorPersonAlreadyExistsException;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.ResourceNotFoundException;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.CategoryPersonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CategoryPersonServiceImp implements CategoryPersonService {

    private final CategoryPersonRepository categorpersonRepository;
    private final PersonRepository personRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryPersonServiceImp(CategoryPersonRepository categorpersonRepository, PersonRepository personRepository, CategoryRepository categoryRepository) {
        this.categorpersonRepository = categorpersonRepository;
        this.personRepository = personRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public CategoryPerson AddCategoryToPerson(BigDecimal monthlyLimit, Long id_category, Long id_person) {
        if(monthlyLimit.compareTo(BigDecimal.ZERO) < 0){
            throw  new RuntimeException(String.format("The monthly Limit cannot be negative: %s", monthlyLimit));
        }else{

            Person person = this.personRepository.findById(id_person).orElseThrow(()->{
                throw new ResourceNotFoundException("Person", "Id", id_person.toString());
            });

            Category category = this.categoryRepository.findById(id_category).orElseThrow(()->{
                throw new ResourceNotFoundException("category", "Id", id_category.toString());
            });

            this.categorpersonRepository.findByCategory_IdAndPerson_Id(id_category,id_person).ifPresent( categoryPerson ->{
                throw new CategorPersonAlreadyExistsException("Person Category already  exists.");
            });


            // the categorisation not exist now we shouldGet all the categorisation and check if we can add this monthly amount
            List<CategoryPerson> categoryPersonList = this.categorpersonRepository.findAllByPerson_Id(id_person);
            BigDecimal totalOfAllMonthlyLimits = new BigDecimal(0);
            for (CategoryPerson categoryPerson: categoryPersonList) {
                totalOfAllMonthlyLimits =  totalOfAllMonthlyLimits.add(categoryPerson.getMonthlyLimit());
            }

            BigDecimal newTotalMonthlyLimits = totalOfAllMonthlyLimits.add(monthlyLimit);

            if(person.getTotalBudget().compareTo(newTotalMonthlyLimits) < 0){
                throw new RuntimeException("Adding this monthly limit exceeds the total budget");
            }

            return this.categorpersonRepository.save(new CategoryPerson(category, person, monthlyLimit));
        }
    }

    @Transactional
    @Override
    public CategoryPerson UpdateMonthlyLimit(BigDecimal monthlyLimit, Long id_category, Long id_person) {
        if(monthlyLimit.compareTo(BigDecimal.ZERO) < 0){
            throw  new RuntimeException(String.format("The monthly Limit cannot be negative: %s", monthlyLimit));
        }else{
            Person person = this.personRepository.findById(id_person).orElseThrow(()->{
                throw new ResourceNotFoundException("Person", "Id", id_person.toString());
            });

            Category category = this.categoryRepository.findById(id_category).orElseThrow(()->{
                throw new ResourceNotFoundException("category", "Id", id_category.toString());
            });

            CategoryPerson categoryPerson = this.categorpersonRepository.findByCategory_IdAndPerson_Id(id_category,id_person).orElseThrow(()->{
                throw new ResourceNotFoundException("category Person", "Id_Category and Id_person", id_category+" ,"+ id_person);
            });

            // the categorisation not exist now we shouldGet all the categorisation and check if we can add this monthly amount
            List<CategoryPerson> categoryPersonList = this.categorpersonRepository.findAllByPerson_Id(id_person);
            BigDecimal totalOfAllMonthlyLimits = new BigDecimal(0);
            for (CategoryPerson categoryPerson1: categoryPersonList) {
                totalOfAllMonthlyLimits =  totalOfAllMonthlyLimits.add(categoryPerson1.getMonthlyLimit());
            }

            BigDecimal totalBudgeWithoudCureantMonyhlyBudge = totalOfAllMonthlyLimits.subtract(categoryPerson.getMonthlyLimit());
            BigDecimal totalBudgeWithewMonthlyBudge = totalBudgeWithoudCureantMonyhlyBudge.add(monthlyLimit);

            if(person.getTotalBudget().compareTo(totalBudgeWithewMonthlyBudge) < 0){
                throw new RuntimeException("Adding this monthly limit exceeds the total budget ");
            }

            categoryPerson.setMonthlyLimit(monthlyLimit);

            return this.categorpersonRepository.save(categoryPerson);
        }
    }

    @Transactional
    @Override
    public void deleteCategoryPerson(Long idCategoryPerson) {
        this.categorpersonRepository.deleteById(idCategoryPerson);
    }

    @Override
    public List<CategoryPerson> getAllCategoryPerson(Long personId) {
        return this.categorpersonRepository.findAllByPerson_Id(personId);
    }


}
