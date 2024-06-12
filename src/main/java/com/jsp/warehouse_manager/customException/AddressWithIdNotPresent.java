package com.jsp.warehouse_manager.customException;

public class AddressWithIdNotPresent extends RuntimeException {
    public String message;

    public AddressWithIdNotPresent(String message)
    {
        this.message=message;
    }

    public String getMessage()
    {
        return message;
    }
}
