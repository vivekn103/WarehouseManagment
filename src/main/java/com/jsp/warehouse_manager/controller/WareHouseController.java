package com.jsp.warehouse_manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wharehouse")
public class WareHouseController {

    @GetMapping("/warehouses")
    public String createWarehouse()
    {
        return "Warehouse found";
    }
}
