package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.implementation;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.*;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.*;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.ResourceNotFoundException;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImp implements TransactionService {

    private final PersonRepository personRepository;
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final CategoryPersonRepository categoryPersonRepository;

    private final BudgetHistoryRepository budgetHistoryRepository;

    private final CategoryLimitHistoryRepository categoryLimitHistoryRepository;

    @Autowired
    public TransactionServiceImp(PersonRepository personRepository, TransactionRepository transactionRepository, CategoryRepository categoryRepository, PaymentTypeRepository paymentTypeRepository, CategoryPersonRepository categoryPersonRepository, BudgetHistoryRepository budgetHistoryRepository, CategoryLimitHistoryRepository categoryLimitHistoryRepository) {
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.paymentTypeRepository = paymentTypeRepository;
        this.categoryPersonRepository = categoryPersonRepository;
        this.budgetHistoryRepository = budgetHistoryRepository;
        this.categoryLimitHistoryRepository = categoryLimitHistoryRepository;
    }


    @Transactional
    @Override
    public Transaction saveTransaction(Long person_id, Long id_category, Long id_typePayment, BigDecimal amount) {

        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("The amount cannot be negative: " + amount);
        }else{
            Person person = this.personRepository.findById(person_id).orElseThrow(()->{
                throw new ResourceNotFoundException("Person", "Id", person_id.toString());
            });


            if(person.getCurrenttotalBudget().compareTo(amount) < 0){
                throw new RuntimeException(String.format("Not enough budget. Available: %s, Required: %s", person.getCurrenttotalBudget(), amount));
            }else{

                // get the monthly limit for the category
                CategoryPerson categoryPerson =  this.categoryPersonRepository.findByCategory_IdAndPerson_Id(id_category, person_id).orElseThrow(()->{
                    throw new RuntimeException("Monthly limit not set for category");
                });

                BigDecimal newTotalSpentCategory = categoryPerson.getTotalSpent().add(amount);

                if(categoryPerson.getMonthlyLimit().compareTo(newTotalSpentCategory) < 0){
                    throw new RuntimeException("Monthly limit reached for the category");
                }else{
                    Category category = this.categoryRepository.findById(id_category).orElseThrow(()->{
                        throw new ResourceNotFoundException("category", "id_category", id_category.toString());
                    });

                    PaymentType paymentType = this.paymentTypeRepository.findById(id_typePayment).orElseThrow(()->{
                        throw new ResourceNotFoundException("PaymentType", "id_typePayment", id_typePayment.toString());
                    });

                    BigDecimal oldTotalCurrentsBudge = person.getCurrenttotalBudget();
                    BigDecimal newTotalBudge = oldTotalCurrentsBudge.subtract(amount);
                    person.setTotalBudgetSpend(newTotalSpentCategory);

                    person.setCurrenttotalBudget(newTotalBudge);
                    LocalDateTime date = LocalDateTime.now();
                    BudgetHistory budgetHistory =  this.budgetHistoryRepository.save(new BudgetHistory(person,person.getTotalBudget(),oldTotalCurrentsBudge, newTotalBudge, date));

                    budgetHistory = this.budgetHistoryRepository.save(budgetHistory);

                    CategoryLimitHistory categoryLimitHistory = new CategoryLimitHistory(category, person, categoryPerson.getMonthlyLimit(),
                            newTotalSpentCategory,date);
                    categoryPerson.setTotalSpent(newTotalSpentCategory);
                    this.categoryPersonRepository.save(categoryPerson);

                    categoryLimitHistory = this.categoryLimitHistoryRepository.save(categoryLimitHistory);

                    return this.transactionRepository.save(new Transaction(person, amount, category, budgetHistory, new Date(), paymentType, categoryLimitHistory));
                }

            }
        }
    }

    @Override
    public List<Transaction> getAllTransactionsByPersonId(Long personID) {
        this.personRepository.findById(personID).orElseThrow(()->{
            throw new ResourceNotFoundException("Person", "Id", personID.toString());
        });
        return this.transactionRepository.findByPerson_Id(personID);
    }

    @Override
    public List<Transaction> getAllPersonTransactionsByCategory(Long personId , String Categoryname) {
        this.personRepository.findById(personId).orElseThrow(()->{
            throw new ResourceNotFoundException("Person", "Id", personId.toString());
        });
        Category category = this.categoryRepository.findByName(Categoryname).orElseThrow(()->{
            throw new ResourceNotFoundException("category", "name", Categoryname);
        });

        return this.transactionRepository.findByCategory_IdAndPerson_Id(category.getId(), personId);
    }

    public List<Transaction> getAllPersonTransactionsInThisMonth(Long personId) {
        // Get the current year and month
        YearMonth currentYearMonth = YearMonth.now();

        // Get the first and last days of the current month
        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        LocalDate lastDayOfMonth = currentYearMonth.atEndOfMonth();

        // Convert LocalDate to Date
        Date startDate = Date.from(firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return transactionRepository.findByPerson_IdAndDateBetween(personId, startDate, endDate);
    }

    @Override
    public List<Transaction> getAllPersonTransactionsLastWeek(Long personId) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the start and end dates of the previous week
        LocalDate startOfLastWeek = currentDate.with(java.time.temporal.TemporalAdjusters.previous(DayOfWeek.MONDAY));
        LocalDate endOfLastWeek = startOfLastWeek.plusDays(6); // Sunday is the end of the week

        // Convert LocalDate to Date
        Date startDate = Date.from(startOfLastWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfLastWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Transaction> transactions = transactionRepository.findByPerson_IdAndDateBetween(personId, startDate, endDate);
        return transactions;
    }

    @Override
    public List<Transaction> getAllPersonTransactionsByCategoryLastWeek(Long personId, String category) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the start and end dates of the previous week
        LocalDate startOfLastWeek = currentDate.with(java.time.temporal.TemporalAdjusters.previous(DayOfWeek.MONDAY));
        LocalDate endOfLastWeek = startOfLastWeek.plusDays(6); // Sunday is the end of the week

        // Convert LocalDate to Date
        Date startDate = Date.from(startOfLastWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfLastWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Query transactions by person ID, category, and date range for the previous week
        List<Transaction> transactions = transactionRepository.findByPerson_IdAndCategory_NameAndDateBetween(
                personId, category,startDate, endDate
        );
        System.out.println(transactions);
        return transactions;

    }
}
