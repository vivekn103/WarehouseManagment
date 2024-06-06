package com.jsp.warehouse_manager.customException;

public class IllegalModificationException extends RuntimeException{

    String message;

    public IllegalModificationException(String message){
        this.message=message;
    }

    public String getMessage()
    {
        return message;
    }
}
