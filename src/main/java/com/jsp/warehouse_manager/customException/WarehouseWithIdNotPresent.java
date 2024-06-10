package com.jsp.warehouse_manager.customException;

public class WarehouseWithIdNotPresent extends RuntimeException {

    String message;
     public WarehouseWithIdNotPresent(String message)
     {
        this.message=message;
     }

     public String getMessage()
     {
        return message;
     }
}
