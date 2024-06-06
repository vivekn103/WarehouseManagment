package com.jsp.warehouse_manager.aminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse_manager.adminRequestDTO.AdminRequest;
import com.jsp.warehouse_manager.adminResponseDTO.AdminResponse;
import com.jsp.warehouse_manager.adminService.AdminService;
import com.jsp.warehouse_manager.utility.ResponseStructure;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(@RequestBody @Valid AdminRequest adminRequest){
        
        return adminService.saveAdminToDB(adminRequest);
    }
}
