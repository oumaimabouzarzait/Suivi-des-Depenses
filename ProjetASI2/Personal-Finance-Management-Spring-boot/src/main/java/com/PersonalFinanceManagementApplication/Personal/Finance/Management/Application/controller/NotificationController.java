package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.controller;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.NotificationService;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/NotificationController", produces = {MediaType.APPLICATION_JSON_VALUE})
public class NotificationController {

    private final NotificationService notificationService;
    private final PersonService personService;


    public NotificationController(NotificationService notificationService, PersonService personService) {
        this.notificationService = notificationService;
        this.personService = personService;

    }

    @GetMapping("/generateWeeklyReport")
    ResponseEntity<String> generateWeeklyReport(@RequestParam Long personId){
        System.out.println("personId ========");
        System.out.println(personId);
        System.out.println("personId ========");
        Person person = this.personService.getPersonById(personId);
        String weeklyRaport = this.notificationService.generateWeeklyReport(person);
        return ResponseEntity.status(HttpStatus.OK).body(weeklyRaport);
    }

    @GetMapping("/sendReport")
    ResponseEntity<String> sendEmail(@RequestParam Long personId,@RequestParam String email ){
        Person person = this.personService.getPersonById(personId);
        String weeklyRaport = this.notificationService.generateWeeklyReport(person);
        this.notificationService.sendEmail(email,"weekley repport", weeklyRaport);
        return ResponseEntity.status(HttpStatus.OK).body(weeklyRaport);
    }


}
