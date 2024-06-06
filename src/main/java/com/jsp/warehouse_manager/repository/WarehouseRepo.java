package com.jsp.warehouse_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse_manager.entity.WareHouse;

public interface WarehouseRepo extends JpaRepository<WareHouse,Integer> {

}
