package com.lifepill.employeeService.service;

import com.lifepill.employeeService.dto.EmployerDTO;
import com.lifepill.employeeService.dto.EmployerWithoutImageDTO;

public interface EmployerService {

    void saveEmployerWithoutImage(EmployerWithoutImageDTO cashierWithoutImageDTO);

    void saveEmployer(EmployerDTO employerDTO);

    EmployerDTO getEmployerById(long employerId);
}
