package com.jsp.warehouse_manager.utility;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.warehouse_manager.customException.IllegalModificationException;
import com.jsp.warehouse_manager.customException.WarehouseWithIdNotPresent;



@RestControllerAdvice
public class HandlerAdapterClass {

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerIllegalModification(IllegalModificationException e)
    {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body( new ErrorStructure<String>()
                    .setErrorMessage("Forbidden To Create Another SUPER_ADMIN")
                    .setStatuscode(HttpStatus.FORBIDDEN.value())
                    .setRootCause(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<Map<String, String>>> handlerMethodArgumentNotValid(MethodArgumentNotValidException e)
    {
        List<ObjectError> allErrors = e.getAllErrors();

        Map<String,String> m  = new LinkedHashMap<String,String>();

        allErrors.forEach(errors -> {
           FieldError fieldError=(FieldError)errors;
           m.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ErrorStructure<Map<String,String>>()
                    .setRootCause(m)
                    .setStatuscode(HttpStatus.BAD_REQUEST.value())
                    .setErrorMessage("Pleas FIll the fields properly")
        );
    }


    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerUsernameNotFoundException(UsernameNotFoundException e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ErrorStructure<String>()
                    .setErrorMessage("Forbidden To Create Another SUPER_ADMIN")
                    .setStatuscode(HttpStatus.BAD_REQUEST.value())
                    .setRootCause(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerwarehouseWithIdNotPresent(WarehouseWithIdNotPresent e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ErrorStructure<String>()
                    .setErrorMessage("Warehouse with id is not present")
                    .setStatuscode(HttpStatus.NOT_FOUND.value())
                    .setRootCause(e.getMessage()));
    }
}
