package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Optional;
import java.util.function.Function;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String categoryName);
}
