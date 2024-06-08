package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.controller;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/person", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/updatePerson")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        person = this.personService.updatePersonInfo(person);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @GetMapping("getPersonById")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Person> getPersonById(@RequestParam Long parsonId){
        Person person = this.personService.getPersonById(parsonId);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @GetMapping("/getAllPersons")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> personList = this.personService.getAllPersons();
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

}
