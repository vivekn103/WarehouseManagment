package com.jsp.warehouse_manager.enums;
import java.util.*;

public enum AdminType {
    SUPER_ADMIN(List.of(Privilages.CREATE_ADMIN,
                        Privilages.CREATE_WAREHOUSE,
                        Privilages.CREATE_ADDRESS,
                        Privilages.CREATE_STORAGE,
                        Privilages.REAR,
                        Privilages.UPDATE_ADDRESS,
                        Privilages.UPDATE_ADMIN,
                        Privilages.UPDATE_WAREHOUSE,
                        Privilages.UPDATE_STORAGE,
                        Privilages.DEL_ADMIN,
                        Privilages.DEL_WAREHOUSE,
                        Privilages.DEL_ADDRESS,
                        Privilages.DEL_STORAGE
                        )),
    ADMIN(List.of(Privilages.CREATE_STORAGE,
                    Privilages.REAR, 
                    Privilages.UPDATE_ADMIN,
                    Privilages.UPDATE_STORAGE,
                    Privilages.DEL_STORAGE
    ));


    public List<Privilages> privilage;

    AdminType(List<Privilages> privilages)
    {
        this.privilage=privilages;
    }

    public List<Privilages> getPrivilages()
    {
        return privilage;
    }
}
