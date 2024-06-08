package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.controller;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Transaction;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject.TransactionDto;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/transaction", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody TransactionDto transactionDto){
        Transaction transaction = this.transactionService.saveTransaction(transactionDto.getPerson_id(), transactionDto.getId_category(),
                transactionDto.getId_typePayment(), transactionDto.getAmount());
        return  ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @GetMapping("/getAllPersonTransactionsInThisMonth")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Transaction>> getAllPersonTransactionsInThisMonth(@RequestParam Long personId){
        List<Transaction> transactions = this.transactionService.getAllPersonTransactionsInThisMonth(personId);
        return  ResponseEntity.status(HttpStatus.OK).body(transactions);
    }

    @GetMapping("/getAllTransactionsByPersonId")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Transaction>> getAllTransactionsByPersonId(@RequestParam Long personId){
        List<Transaction> transactions = this.transactionService.getAllTransactionsByPersonId(personId);
        return  ResponseEntity.status(HttpStatus.OK).body(transactions);
    }
    @GetMapping("/getAllPersonTransactionsByCategory")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Transaction>> getAllPersonTransactionsByCategory(@RequestParam Long personId, String categoryName){
        List<Transaction> transactions = this.transactionService.getAllPersonTransactionsByCategory(personId, categoryName);
        return  ResponseEntity.status(HttpStatus.OK).body(transactions);
    }


    @GetMapping("getAllPersonTransactionsByCategoryLastWeek")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Transaction>> getAllPersonTransactionsByCategoryLastWeek(@RequestParam Long personId, String categoryName){
        List<Transaction> transactions = this.transactionService.getAllPersonTransactionsByCategory(personId, categoryName);
        return  ResponseEntity.status(HttpStatus.OK).body(transactions);
    }


}
