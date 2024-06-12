package com.jsp.warehouse_manager.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
    private int id;
    private String addressLine;
    private String city;
    private String state;
    private int pincode;
    private String longitude;
    private String latitude;
    
}
