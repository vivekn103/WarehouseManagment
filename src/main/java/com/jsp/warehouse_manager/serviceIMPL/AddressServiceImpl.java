package com.jsp.warehouse_manager.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_manager.customException.AddressWithIdNotPresent;
import com.jsp.warehouse_manager.customException.WarehouseWithIdNotPresent;
import com.jsp.warehouse_manager.entity.Address;
import com.jsp.warehouse_manager.mapper.MapperUtility;
import com.jsp.warehouse_manager.repository.AddressRepo;
import com.jsp.warehouse_manager.repository.WarehouseRepo;
import com.jsp.warehouse_manager.requestDTO.AddressRequest;
import com.jsp.warehouse_manager.responseDTO.AddressResponse;
import com.jsp.warehouse_manager.service.AddressService;
import com.jsp.warehouse_manager.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private MapperUtility mapper;

    @Override
    public ResponseEntity<ResponseStructure<AddressResponse>> mapAddressToWarehouse(AddressRequest addressRequest,int warehouseId) {
       return  warehouseRepo.findById(warehouseId).map(
            warehouse ->{
                Address address = mapper.mapRequestToAddress(addressRequest, new Address());
                address.setWarehouse(warehouse);

                address = addressRepo.save(address);

                return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AddressResponse>()
                                        .setData(mapper.mapTOAddressResponse(address))
                                        .setMessage("Address Is Mapped to Respective Warehouse")
                                        .setStatuscode(HttpStatus.CREATED.value()));       
            }
        ).orElseThrow(()-> new WarehouseWithIdNotPresent("Warehouse with id is not present"));   
    }

    @Override
    public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest, int addressId) {

      return addressRepo.findById(addressId).map(exaddress ->{
                Address address = mapper.mapRequestToAddress(addressRequest, new Address());
                exaddress.setAddressLine(address.getAddressLine());
                exaddress.setCity(address.getCity());
                exaddress.setPincode(address.getPincode());
                exaddress.setState(address.getState());
                exaddress.setLatitude(address.getLatitude());
                exaddress.setLongitude(address.getLongitude());

                exaddress=addressRepo.save(exaddress);

                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseStructure<AddressResponse>()
                        .setData(mapper.mapTOAddressResponse(exaddress))
                        .setMessage("Address Modified Successfully")
                        .setStatuscode(HttpStatus.OK.value())
                );
       })
       .orElseThrow(()-> new AddressWithIdNotPresent("Address With Id Not Present"));
    }

    @Override
    public ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(int addressId) {
    
       return addressRepo.findById(addressId).map(address -> {
            return ResponseEntity.status(HttpStatus.FOUND).
                        body(new ResponseStructure<AddressResponse>()
                                .setData(mapper.mapTOAddressResponse(address))
                                .setMessage("Address Modified Successfully")
                                .setStatuscode(HttpStatus.OK.value()))  ; 
        })
        .orElseThrow(()-> new AddressWithIdNotPresent("Address With Id Not Present"));
    }

    

}
