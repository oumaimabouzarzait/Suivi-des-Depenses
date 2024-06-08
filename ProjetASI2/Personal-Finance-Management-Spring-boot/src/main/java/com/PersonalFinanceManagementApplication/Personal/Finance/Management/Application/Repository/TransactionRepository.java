package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByPerson_Id(Long personId);
    List<Transaction> findByCategory_IdAndPerson_Id(Long categoryId, Long person_id);
    List<Transaction> findByPerson_IdAndDateBetween(Long personId, Date startDate, Date endDate);

    List<Transaction> findByPerson_IdAndCategory_NameAndDateBetween(Long personId, String Category_Name, Date startDate, Date endDate);

}
