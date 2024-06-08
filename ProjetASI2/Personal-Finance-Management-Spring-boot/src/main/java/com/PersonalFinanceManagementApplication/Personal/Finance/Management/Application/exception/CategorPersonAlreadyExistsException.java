package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CategorPersonAlreadyExistsException extends RuntimeException{
    public CategorPersonAlreadyExistsException(String message){
        super(message);
    }

    public CategorPersonAlreadyExistsException(){
        super();
    }
}
