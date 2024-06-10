package com.jsp.warehouse_manager.entity;

import org.springframework.stereotype.Component;

import com.jsp.warehouse_manager.enums.AdminType;
import com.jsp.warehouse_manager.enums.Privilages;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String adminName;
    private String adminEmail;
    private String password;
   @Enumerated(EnumType.STRING)
    private AdminType adminType;
   
    // private WareHouse warehouse;
}
