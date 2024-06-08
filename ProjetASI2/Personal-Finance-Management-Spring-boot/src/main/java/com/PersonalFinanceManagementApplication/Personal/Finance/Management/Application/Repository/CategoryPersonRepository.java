package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.CategoryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryPersonRepository extends JpaRepository<CategoryPerson,Long> {
    Optional<CategoryPerson> findByCategory_IdAndPerson_Id(Long categoryId, Long personId);

    List<CategoryPerson> findAllByPerson_Id(Long personId);
}
