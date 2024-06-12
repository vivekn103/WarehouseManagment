package com.jsp.warehouse_manager.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse_manager.requestDTO.AddressRequest;
import com.jsp.warehouse_manager.responseDTO.AddressResponse;
import com.jsp.warehouse_manager.service.AddressService;
import com.jsp.warehouse_manager.utility.ResponseStructure;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PreAuthorize("hasAuthority('CREATE_ADDRESS')")
    @PostMapping("/warehouses/{warehouseId}/address")
    public ResponseEntity<ResponseStructure<AddressResponse>> mapAddressToWarehouse(@RequestBody @Valid AddressRequest addressRequest,@PathVariable("warehouseId") int warehouseId) {
       
        return addressService.mapAddressToWarehouse(addressRequest, warehouseId);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADDRESS')")
    @PutMapping("/address/{addressId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@RequestBody @Valid AddressRequest addressRequest,@PathVariable("addressId") int addressId) {
       
        return addressService.updateAddress(addressRequest, addressId);
    }


    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/address/{addressId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(@PathVariable("addressId") int addressId) {
       
        return addressService.findAddressById(addressId);
    }
    
}
