package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentTypeRepository extends JpaRepository<PaymentType,Long> {
    Optional<PaymentType> findByName(String name);
}
