package com.jsp.warehouse_manager.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_manager.entity.WareHouse;
import com.jsp.warehouse_manager.mapper.MapperUtility;
import com.jsp.warehouse_manager.repository.WarehouseRepo;
import com.jsp.warehouse_manager.requestDTO.WarehouseRequest;
import com.jsp.warehouse_manager.responseDTO.WarehouseResponse;
import com.jsp.warehouse_manager.service.WarehouseService;
import com.jsp.warehouse_manager.utility.ResponseStructure;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private MapperUtility mapper;

    @Override
    public ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(WarehouseRequest warehouseRequest) {

           
        WareHouse warehouse = warehouseRepo.save(mapper.mapRequestToWarehouse(warehouseRequest, new WareHouse()));
        //     Collection<SimpleGrantedAuthority> c = new ArrayList<SimpleGrantedAuthority>();
        //    SimpleGrantedAuthority granted =new SimpleGrantedAuthority("CREATE_WAREHOUSE");
        //    c.add(granted);

            // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            // auth.getAuthorities().contains(c);
            // .stream()
            // .map(listOfAuthentication -> listOfAuthentication.getA));

        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ResponseStructure<WarehouseResponse>()
            .setData(mapper.mapTOWarehouseResponse(warehouse))
            .setMessage("Warehouse Created")
            .setStatuscode(HttpStatus.CREATED.value())
        );

    }

    @Override
    public ResponseEntity<ResponseStructure<WarehouseResponse>> updateWarehouse(WarehouseRequest warehouseRequest,
            int warehouseId) {

                return warehouseRepo.findById(warehouseId)
                        .map(warehouse ->{

                            WareHouse w =mapper.mapRequestToWarehouse(warehouseRequest,new WareHouse());
                            warehouse.setWarehouseName(w.getWarehouseName());
                            warehouse.setAdmin(w.getAdmin());

                            warehouse=warehouseRepo.save(warehouse);

                            return ResponseEntity.status(HttpStatus.ACCEPTED)
                                    .body(new ResponseStructure<WarehouseResponse>()
                                    .setData(mapper.mapTOWarehouseResponse(warehouse))
                                    .setMessage("Successfully Modified The Warehouse")
                                    .setStatuscode(HttpStatus.ACCEPTED.value()));
                        })
                        .orElseThrow();
        
    }

    @Override
    public ResponseEntity<ResponseStructure<WarehouseResponse>> fetchWarehouse(int warehouseId) {
        
         return   warehouseRepo.findById(warehouseId).map( (warehouses) ->{
               

               return ResponseEntity.status(HttpStatus.FOUND)
                        .body(new ResponseStructure<WarehouseResponse>()
                                .setData(mapper.mapTOWarehouseResponse(warehouses))
                                .setMessage("Found")
                                .setStatuscode(HttpStatus.FOUND.value()));
                        
            }).orElseThrow();

    }


}
