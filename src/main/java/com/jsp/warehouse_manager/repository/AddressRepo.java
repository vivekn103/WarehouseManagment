package com.jsp.warehouse_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.warehouse_manager.entity.Address;


@Repository
public interface AddressRepo extends JpaRepository<Address,Integer > {

}
