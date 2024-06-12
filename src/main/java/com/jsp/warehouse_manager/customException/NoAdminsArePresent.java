package com.jsp.warehouse_manager.customException;

public class NoAdminsArePresent extends RuntimeException {
    String message;
    public NoAdminsArePresent(String message)
    {
        this.message=message;
    }

    public String getMessage()
    {
        return message;
    }
}

