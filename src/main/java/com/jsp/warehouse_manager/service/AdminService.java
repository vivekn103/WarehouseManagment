package com.jsp.warehouse_manager.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_manager.entity.Admin;
import com.jsp.warehouse_manager.requestDTO.AdminRequest;
import com.jsp.warehouse_manager.responseDTO.AdminResponse;
import com.jsp.warehouse_manager.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AdminService {
    public ResponseEntity<ResponseStructure<AdminResponse>> saveAdminToDB(AdminRequest admin);

    public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest, int varehouseId);

    public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest);

    public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest,
            int adminId);

    public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int adminId);

    public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmins();
}
