package com.jsp.warehouse_manager.mapper;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.warehouse_manager.entity.Address;
import com.jsp.warehouse_manager.entity.Admin;
import com.jsp.warehouse_manager.entity.WareHouse;
import com.jsp.warehouse_manager.requestDTO.AddressRequest;
import com.jsp.warehouse_manager.requestDTO.AdminRequest;
import com.jsp.warehouse_manager.requestDTO.WarehouseRequest;
import com.jsp.warehouse_manager.responseDTO.AddressResponse;
import com.jsp.warehouse_manager.responseDTO.AdminResponse;
import com.jsp.warehouse_manager.responseDTO.WarehouseResponse;

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

    public WareHouse mapRequestToWarehouse(WarehouseRequest request,WareHouse warehouse)
    {
        warehouse.setWarehouseName(request.getWarehouseName());
         return warehouse;
    } 


    public WarehouseResponse mapTOWarehouseResponse(WareHouse warehouse){
        return WarehouseResponse.builder().warehouseName(warehouse.getWarehouseName())
                        .id(warehouse.getWareHouseId())
                        .totalCapacity(0).build();
    }

    public Address mapRequestToAddress(AddressRequest addressRequest,Address address)
    {
        return Address.builder()
                    .addressLine(addressRequest.getAddressLine())
                    .city(addressRequest.getCity())
                    .pincode(addressRequest.getPincode())
                    .state(addressRequest.getState())
                    .latitude(addressRequest.getLatitude())
                    .longitude(addressRequest.getLongitude())
                    .build();
    }

    public AddressResponse mapTOAddressResponse(Address address)
    {
        return AddressResponse.builder()
                    .addressLine(address.getAddressLine())
                    .city(address.getCity())
                    .id(address.getId())
                    .state(address.getState())
                    .pincode(address.getPincode())
                    .latitude(address.getLatitude())
                    .longitude(address.getLongitude())
                    .build();
    }
}
