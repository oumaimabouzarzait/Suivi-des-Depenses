package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    private String newCategoryName;

    public CategoryDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryDto(String name, String description, String newCategoryName) {
        this.name = name;
        this.description = description;
        this.newCategoryName = newCategoryName;
    }

    public static Category mapToCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setId(categoryDto.getId());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

}
