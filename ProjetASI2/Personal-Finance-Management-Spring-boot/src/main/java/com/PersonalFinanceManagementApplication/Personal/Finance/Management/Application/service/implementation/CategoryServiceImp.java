package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.implementation;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Category;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Repository.CategoryRepository;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.ResourceNotFoundException;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        this.categoryRepository.findByName(category.getName()).ifPresent(presentCategory ->{
            throw new RuntimeException(" Category already  exists.");
        });
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(String categoryName, String newCategoryName, String description) {
         Category category = this.categoryRepository.findByName(categoryName).orElseThrow(()->{
            throw new ResourceNotFoundException("category", "categoryName ", categoryName);
        });
        category.setName(newCategoryName);
        category.setDescription(description);
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategorys() {
        return this.categoryRepository.findAll();
    }
}
