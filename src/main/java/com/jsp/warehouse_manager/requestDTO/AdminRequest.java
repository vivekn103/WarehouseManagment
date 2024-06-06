package com.jsp.warehouse_manager.requestDTO;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRequest {
    @NotBlank(message = "Name Cannot Be Null")
    @NotNull(message = "Name Cannot be Blank")
    private String adminName;

    @Email(regexp = "^[\\w\\.-]+@(gmail|GMAIL)\\.(com|COM)$",message = "email should containg @gmail.com at the end" )
    private String adminEmail;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least one letter, one number, one special character")
    private String password;
}
