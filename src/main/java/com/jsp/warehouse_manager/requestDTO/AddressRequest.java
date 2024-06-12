package com.jsp.warehouse_manager.requestDTO;

import com.jsp.warehouse_manager.entity.WareHouse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.NonFinal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest {
    @NotBlank(message = "Address cannot be Blank")
    @NotNull(message = "Address cannot be Null")
    private String addressLine;
    @NotBlank(message = "City cannot be Blank")
    @NotNull(message = "City cannot be Null")
    private String city;
    @NotBlank(message = "State cannot be Blank")
    @NotNull(message = "State cannot be Null")
    private String state;
    @NotBlank(message = "pincode cannot be Blank")
    @NotNull(message = "pincode cannot be Null")
    private int pincode;
    // @NotBlank(message = "Address cannot be Blank")
    // @NotNull(message = "Address cannot be Null")
    private String longitude;
    // @NotBlank(message = "Address cannot be Blank")
    // @NotNull(message = "Address cannot be Null")
    private String latitude;
    
}
