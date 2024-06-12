package com.jsp.warehouse_manager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jsp.warehouse_manager.requestDTO.AddressRequest;
import com.jsp.warehouse_manager.responseDTO.AddressResponse;
import com.jsp.warehouse_manager.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AddressService {
    public ResponseEntity<ResponseStructure<AddressResponse>> mapAddressToWarehouse(@RequestBody AddressRequest addressRequest,@PathVariable("warehouseId") int warehouseId);

    public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@Valid AddressRequest addressRequest,
            int addressId);

    public ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(int addressId);
}
