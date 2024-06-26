package com.lifepill.employeeService.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Employer password reset dto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployerPasswordResetDTO {
    private long employerId;
    private String employerPassword;
}
