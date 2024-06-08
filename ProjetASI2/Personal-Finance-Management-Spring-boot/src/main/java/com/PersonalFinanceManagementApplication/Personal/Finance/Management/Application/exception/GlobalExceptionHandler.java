package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception;


import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception excaption,
                                                                  WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,excaption.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handlePersonAllReadyExistException(PersonAlreadyExistsException excaption,
                                                                               WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.BAD_REQUEST,excaption.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidTokenException.class)
    public ResponseEntity<ErrorResponseDto> handlePersonAllReadyExistException(NotValidTokenException excaption,
                                                                               WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.BAD_REQUEST,excaption.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CategorPersonAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handlePersonAllReadyExistException(CategorPersonAlreadyExistsException excaption,
                                                                               WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.BAD_REQUEST,excaption.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException excaption,
                                                                            WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.NOT_FOUND,excaption.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }
}
