package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Category;

import java.util.List;

public interface CategoryService {
    public Category addCategory(Category category);

    public Category updateCategory(String categoryName, String newCategoryName, String description);

    public List<Category> getAllCategorys();


}
