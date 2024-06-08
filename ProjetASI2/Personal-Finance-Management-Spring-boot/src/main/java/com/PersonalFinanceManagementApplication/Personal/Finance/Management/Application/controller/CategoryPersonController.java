package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.controller;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.CategoryPerson;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject.CategoryPersonDto;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject.ResponseDto;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.CategoryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categoryPerson", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CategoryPersonController {

    private final CategoryPersonService categoryPersonService;

    @Autowired
    public CategoryPersonController(CategoryPersonService categoryPersonService) {
        this.categoryPersonService = categoryPersonService;
    }

    @PostMapping("/AddCategoryToPerson")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CategoryPerson> AddCategoryToPerson(@RequestBody CategoryPersonDto categoryPersonDto){
        CategoryPerson categoryPerson = this.categoryPersonService.AddCategoryToPerson(categoryPersonDto.getMonthlyLimit(),
                categoryPersonDto.getId_category(), categoryPersonDto.getId_person());
        return  ResponseEntity.status(HttpStatus.CREATED).body(categoryPerson);
    }

    @PostMapping("/UpdateMonthlyLimit")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CategoryPerson> UpdateMonthlyLimit(@RequestBody CategoryPersonDto categoryPersonDto){
        CategoryPerson categoryPerson = this.categoryPersonService.UpdateMonthlyLimit(categoryPersonDto.getMonthlyLimit(),
                categoryPersonDto.getId_category(), categoryPersonDto.getId_person());
        return  ResponseEntity.status(HttpStatus.CREATED).body(categoryPerson);
    }

    @GetMapping("/getAllCategoryPersonById")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<CategoryPerson>> UpdateMonthlyLimit(@RequestParam Long personId){
        List<CategoryPerson> categoryPersons = this.categoryPersonService.getAllCategoryPerson(personId);
        return  ResponseEntity.status(HttpStatus.OK).body(categoryPersons);
    }

    @DeleteMapping("/deleteCategoryPerson")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseDto> deleteCategoryPersonById(@RequestParam Long idCategoryPerson){
        this.categoryPersonService.deleteCategoryPerson(idCategoryPerson);
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201","deleted sussfully"));
    }

}
