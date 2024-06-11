package com.jsp.warehouse_manager.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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


}
