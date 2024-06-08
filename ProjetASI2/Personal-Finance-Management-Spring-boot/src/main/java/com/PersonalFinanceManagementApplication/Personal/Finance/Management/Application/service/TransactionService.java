package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Transaction;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    public Transaction saveTransaction(Long person_id, Long id_category, Long id_typePayment, BigDecimal amount);

    public List<Transaction> getAllTransactionsByPersonId(Long personID);

    public List<Transaction> getAllPersonTransactionsByCategory(Long personId , String Categoryname);

    public List<Transaction> getAllPersonTransactionsInThisMonth(Long personId);

    public List<Transaction> getAllPersonTransactionsLastWeek(Long personId);

    public List<Transaction> getAllPersonTransactionsByCategoryLastWeek(Long personId, String category);

}
