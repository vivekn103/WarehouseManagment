package com.jsp.warehouse_manager.mapper;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.warehouse_manager.entity.Admin;
import com.jsp.warehouse_manager.requestDTO.AdminRequest;
import com.jsp.warehouse_manager.responseDTO.AdminResponse;

@Component
public class MapperUtility {
    @Autowired
    PasswordEncoder passwordEncoder;

    public Admin mapRequestToAdmin(AdminRequest adminRequest,Admin admin)
    {   
        if(admin==null)
        {
            admin =new Admin();
        }
        admin.setAdminEmail(adminRequest.getAdminEmail());
        admin.setAdminName(adminRequest.getAdminName());
        admin.setPassword( passwordEncoder.encode(adminRequest.getPassword()) );

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
