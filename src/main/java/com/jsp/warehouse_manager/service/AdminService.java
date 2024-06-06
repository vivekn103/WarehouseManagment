package com.jsp.warehouse_manager.adminService;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_manager.adminRequestDTO.AdminRequest;
import com.jsp.warehouse_manager.adminResponseDTO.AdminResponse;
import com.jsp.warehouse_manager.entity.Admin;
import com.jsp.warehouse_manager.utility.ResponseStructure;

public interface AdminService {
    public ResponseEntity<ResponseStructure<AdminResponse>> saveAdminToDB(AdminRequest admin);
}
