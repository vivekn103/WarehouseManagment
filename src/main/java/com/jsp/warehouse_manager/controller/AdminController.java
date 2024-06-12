package com.jsp.warehouse_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse_manager.requestDTO.AdminRequest;
import com.jsp.warehouse_manager.responseDTO.AdminResponse;
import com.jsp.warehouse_manager.service.AdminService;
import com.jsp.warehouse_manager.utility.ResponseStructure;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(@RequestBody @Valid AdminRequest adminRequest){
        
        return adminService.saveAdminToDB(adminRequest);
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping("/warehouses/{warehouseId}/admins")
    public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@RequestBody AdminRequest adminRequest,@PathVariable("warehouseId")int warehouseId) {
        
        return adminService.createAdmin(adminRequest,warehouseId);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/admins")
    public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody @Valid AdminRequest adminRequest) {
        return adminService.updateAdmin(adminRequest);
        
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN') && hasAuthority('UPDATE_ADMIN') ")
    @PutMapping("/admins/{adminId}")
    public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(@PathVariable("adminId") int adminId, @RequestBody @Valid AdminRequest adminRequest) {
        
        return adminService.updateAdminBySuperAdmin(adminRequest,adminId);
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/admins/{adminId}")
    public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(@PathVariable("adminId") int adminId) {
        return adminService.findAdmin(adminId);
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/admins")
    public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmins() {
        return adminService.findAllAdmins();
    }
    
    
}
