package com.jsp.warehouse_manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.warehouse_manager.entity.Admin;
import com.jsp.warehouse_manager.enums.AdminType;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{

    // @Query(value = "Select * FROM warehousemanager.admin where admin_type = :adminType",nativeQuery=true)
    // Optional<Admin> existsByAdminType(@Param("adminType") AdminType adminType);

     boolean existsByAdminType(AdminType adminType);

     @Query(value = "Select * FROM warehousemanager.admin where admin_email = :username",nativeQuery=true)
    Optional<Admin> findByEmail(@Param("username") String username);
}
