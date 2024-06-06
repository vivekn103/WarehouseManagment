package com.jsp.warehouse_manager.responseDTO;

import java.util.List;

import com.jsp.warehouse_manager.enums.Privilages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private int id;
    private String adminName;
    private String adminEmail;
    private List<Privilages> privilage;
}
