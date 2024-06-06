package com.jsp.warehouse_manager.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_manager.entity.Admin;
import com.jsp.warehouse_manager.requestDTO.AdminRequest;
import com.jsp.warehouse_manager.responseDTO.AdminResponse;
import com.jsp.warehouse_manager.utility.ResponseStructure;

public interface AdminService {
    public ResponseEntity<ResponseStructure<AdminResponse>> saveAdminToDB(AdminRequest admin);
}
