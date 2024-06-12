package com.jsp.warehouse_manager.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;

import com.jsp.warehouse_manager.customException.AdminWithIdNotPresent;
import com.jsp.warehouse_manager.customException.IllegalModificationException;
import com.jsp.warehouse_manager.customException.NoAdminsArePresent;
import com.jsp.warehouse_manager.customException.WarehouseWithIdNotPresent;
import com.jsp.warehouse_manager.entity.Admin;
import com.jsp.warehouse_manager.enums.AdminType;
import com.jsp.warehouse_manager.mapper.MapperUtility;
import com.jsp.warehouse_manager.repository.AdminRepository;
import com.jsp.warehouse_manager.repository.WarehouseRepo;
import com.jsp.warehouse_manager.requestDTO.AdminRequest;
import com.jsp.warehouse_manager.responseDTO.AdminResponse;
import com.jsp.warehouse_manager.service.AdminService;
import com.jsp.warehouse_manager.utility.ResponseStructure;


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

@Override
public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest) {

      String adminUsername = SecurityContextHolder.getContext().getAuthentication().getName();
      return adminRepo.findByEmail(adminUsername).map(admin -> {
        Admin mappedAdmin = mapper.mapRequestToAdmin(adminRequest, new Admin());
        admin.setAdminEmail(mappedAdmin.getAdminEmail());
        admin.setAdminName(mappedAdmin.getAdminName());
        admin.setPassword(mappedAdmin.getPassword());

        Admin changedAdmin = adminRepo.save(admin);

        return ResponseEntity.status(HttpStatus.OK).body( 
            new ResponseStructure<AdminResponse>()
            .setData(mapper.mapToAdminResponse(changedAdmin))
            .setMessage("Admin Modifies Successfully")
            .setStatuscode(HttpStatus.OK.value()));

      }).orElseThrow(()-> new AdminWithIdNotPresent("Admin With Id Is Not Present"));
  
}

@Override
public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest,
    int adminId) {

      return adminRepo.findById(adminId).map( exadmin -> {

        Admin admin = mapper.mapRequestToAdmin(adminRequest, new Admin());
        exadmin.setAdminEmail(admin.getAdminEmail());
        exadmin.setAdminName(admin.getAdminName());
        exadmin.setPassword(admin.getPassword());

        exadmin = adminRepo.save(exadmin);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseStructure<AdminResponse>()
                  .setData(mapper.mapToAdminResponse(exadmin))
                  .setMessage("Admin Successfully modified")
                  .setStatuscode(HttpStatus.OK.value()));
      })
      
      .orElseThrow(()-> new AdminWithIdNotPresent("Admin with provided id is not present"));
  
}

@Override
public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int adminId) {
  
  return adminRepo.findById(adminId).map(admin ->{
      return ResponseEntity.status(HttpStatus.FOUND)
              .body(new ResponseStructure<AdminResponse>()
              .setData(mapper.mapToAdminResponse(admin))
              .setMessage("Admin Found")
              .setStatuscode(HttpStatus.FOUND.value()));

  }).orElseThrow(()-> new AdminWithIdNotPresent("Admin with provided id is not present"));
}

@Override
public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmins() {
      
  return adminRepo.findAllByAdminType(AdminType.ADMIN.name())
          .map((listOfAdmins) ->{
            
            //List<AdminResponse> res = listOfAdmins.stream().map(adminList -> mapper.mapToAdminResponse(adminList) ).toList();
 
            return ResponseEntity.status(HttpStatus.FOUND).body(
              new ResponseStructure<List<AdminResponse>>()
                    .setData(listOfAdmins
                            .stream().map(adminList -> mapper.mapToAdminResponse(adminList) ).toList())
                    .setStatuscode(HttpStatus.FOUND.value())
                    .setMessage("Admins found")
            );
          }).orElseThrow( ()-> new NoAdminsArePresent("No Admins are Found"));
}

}
