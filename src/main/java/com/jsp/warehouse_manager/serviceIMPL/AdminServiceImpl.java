package com.jsp.warehouse_manager.adminServiceIMPL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_manager.adminRepo.AdminRepository;
import com.jsp.warehouse_manager.adminRequestDTO.AdminRequest;
import com.jsp.warehouse_manager.adminResponseDTO.AdminResponse;
import com.jsp.warehouse_manager.adminService.AdminService;
import com.jsp.warehouse_manager.customException.IllegalModificationException;
import com.jsp.warehouse_manager.entity.Admin;
import com.jsp.warehouse_manager.enums.AdminType;
import com.jsp.warehouse_manager.mapper.MapperUtility;
import com.jsp.warehouse_manager.utility.ResponseStructure;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private MapperUtility mapper;

public ResponseEntity<ResponseStructure<AdminResponse>> saveAdminToDB(AdminRequest adminRequest){

          if(adminRepo.existsByAdminType(AdminType.SUPER_ADMIN))
          {
            throw new IllegalModificationException("It Is Illegal To Create Another SUPER_ADMIN, SUPER_ADMIN Is Already Exist");
          }
          else{ 
                Admin admin2 = mapper.mapRequestToAdmin(adminRequest,new Admin());
                admin2.setAdminType(AdminType.SUPER_ADMIN);
      
        
                Admin a = adminRepo.save(admin2);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                   new ResponseStructure<AdminResponse>().setData(mapper.mapToAdminResponse(a))
                                                            .setMessage("Super AdminCreated")
                                                            .setStatuscode(HttpStatus.CREATED.value())
                );
          }
         
}

}
