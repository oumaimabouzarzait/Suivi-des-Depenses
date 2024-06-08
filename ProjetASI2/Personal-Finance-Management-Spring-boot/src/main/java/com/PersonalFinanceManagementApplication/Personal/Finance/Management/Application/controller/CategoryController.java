package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.controller;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Category;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject.CategoryDto;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Category> saveCategory(@RequestBody CategoryDto categoryDto){
        Category category = CategoryDto.mapToCategory(categoryDto);
        category = this.categoryService.addCategory(category);
        return  ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryDto categoryDto){
        Category category = this.categoryService.updateCategory(categoryDto.getName(), categoryDto.getNewCategoryName(), categoryDto.getDescription());
        return  ResponseEntity.status(HttpStatus.CREATED).body(category);
    }


    @GetMapping("/getAllCategorys")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Category>> getAllCategorys(){
        List<Category> categories = this.categoryService.getAllCategorys();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

}
