package com.jsp.warehouse_manager.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_manager.requestDTO.WarehouseRequest;
import com.jsp.warehouse_manager.responseDTO.WarehouseResponse;
import com.jsp.warehouse_manager.utility.ResponseStructure;

public interface WarehouseService {
    public  ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(WarehouseRequest warehouseRequest);

    public ResponseEntity<ResponseStructure<WarehouseResponse>> updateWarehouse(WarehouseRequest warehouseRequest,
            int warehouseId);

    public ResponseEntity<ResponseStructure<WarehouseResponse>> fetchWarehouse(int warehouseId);
}
