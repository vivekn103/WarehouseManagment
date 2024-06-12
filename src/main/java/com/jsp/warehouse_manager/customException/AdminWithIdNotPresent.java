package com.jsp.warehouse_manager.customException;

public class AdminWithIdNotPresent extends RuntimeException{
    String message;

    public AdminWithIdNotPresent(String message)
    {
        this.message=message;
    }

    public String getMessage(){
        return message;
    }
}
