package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.BudgetHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetHistoryRepository extends JpaRepository<BudgetHistory,Long> {
}
