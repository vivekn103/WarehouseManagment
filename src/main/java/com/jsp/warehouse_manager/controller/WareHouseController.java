package com.jsp.warehouse_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse_manager.requestDTO.WarehouseRequest;
import com.jsp.warehouse_manager.responseDTO.WarehouseResponse;
import com.jsp.warehouse_manager.service.WarehouseService;
import com.jsp.warehouse_manager.utility.ResponseStructure;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class WareHouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PreAuthorize("hasAuthority('CREATE_WAREHOUSE')")
    @PostMapping("/warehouses")
    public ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(@RequestBody WarehouseRequest warehouseRequest) {
        
        System.out.println("WHarehouse Request:"+ warehouseRequest.getWarehouseName());
        return warehouseService.createWarehouse(warehouseRequest);
    }
    
}
