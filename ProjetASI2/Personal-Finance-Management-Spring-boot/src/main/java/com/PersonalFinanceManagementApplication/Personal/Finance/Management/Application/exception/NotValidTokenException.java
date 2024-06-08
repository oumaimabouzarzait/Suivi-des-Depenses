package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotValidTokenException extends RuntimeException{
    public NotValidTokenException(String message){
        super(message);
    }
}
