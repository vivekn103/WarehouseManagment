package com.jsp.warehouse_manager.serviceIMPL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_manager.customException.IllegalModificationException;
import com.jsp.warehouse_manager.customException.WarehouseWithIdNotPresent;
import com.jsp.warehouse_manager.entity.Admin;
import com.jsp.warehouse_manager.entity.WareHouse;
import com.jsp.warehouse_manager.enums.AdminType;
import com.jsp.warehouse_manager.mapper.MapperUtility;
import com.jsp.warehouse_manager.repository.AdminRepository;
import com.jsp.warehouse_manager.repository.WarehouseRepo;
import com.jsp.warehouse_manager.requestDTO.AdminRequest;
import com.jsp.warehouse_manager.responseDTO.AdminResponse;
import com.jsp.warehouse_manager.service.AdminService;
import com.jsp.warehouse_manager.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private MapperUtility mapper;
    @Autowired
    private WarehouseRepo warehouseRepo;

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

@Override
public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest,int warehouseId) {
    

 return warehouseRepo.findById(warehouseId).map(warehouse ->
          {
            Admin admin = mapper.mapRequestToAdmin(adminRequest, new Admin());
            admin.setAdminType(AdminType.ADMIN);

            admin = adminRepo.save(admin);

            warehouse.setAdmin(admin);
            warehouse.setWarehouseName("Hebaal");
            warehouseRepo.save(warehouse);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                  new ResponseStructure<AdminResponse>()
                  .setMessage("Admin Created")
                  .setData(mapper.mapToAdminResponse(admin))
                  .setStatuscode(HttpStatus.CREATED.value()));
          }).orElseThrow(()-> new WarehouseWithIdNotPresent("Warehouse with Id is not present"));
}

}
