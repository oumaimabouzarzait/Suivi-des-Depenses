package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Category;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.CategoryPerson;

import java.math.BigDecimal;
import java.util.List;

public interface CategoryPersonService {

    public CategoryPerson AddCategoryToPerson(BigDecimal monthlyLimit, Long id_category, Long id_person);

    public CategoryPerson UpdateMonthlyLimit(BigDecimal monthlyLimit, Long id_category, Long id_person);

    public void deleteCategoryPerson(Long CategoryPerson);

    public List<CategoryPerson> getAllCategoryPerson(Long personId);
}
