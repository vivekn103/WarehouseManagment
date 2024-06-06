package com.jsp.warehouse_manager.mapper;

import java.util.*;

import org.springframework.stereotype.Component;

import com.jsp.warehouse_manager.adminRequestDTO.AdminRequest;
import com.jsp.warehouse_manager.adminResponseDTO.AdminResponse;
import com.jsp.warehouse_manager.entity.Admin;
import com.jsp.warehouse_manager.enums.Privilages;

@Component
public class MapperUtility {

    public Admin mapRequestToAdmin(AdminRequest adminRequest,Admin admin)
    {   
        if(admin==null)
        {
            admin =new Admin();
        }
        admin.setAdminEmail(adminRequest.getAdminEmail());
        admin.setAdminName(adminRequest.getAdminName());
        admin.setPassword(adminRequest.getPassword());

        return admin;
    } 

    public AdminResponse mapToAdminResponse(Admin admin)
    {   //List<Privilages> p =new ArrayList<Privilages>();

        return AdminResponse.builder()
                .adminName(admin.getAdminName())
                .adminEmail(admin.getAdminEmail())
                .id(admin.getId())
                .build();
    }
}
