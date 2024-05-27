package com.lifepill.employeeService.service;

import com.lifepill.employeeService.dto.EmployerDTO;
import com.lifepill.employeeService.dto.EmployerWithoutImageDTO;
import com.lifepill.employeeService.dto.requestDTO.EmployerAllDetailsUpdateDTO;

public interface EmployerService {

    void saveEmployerWithoutImage(EmployerWithoutImageDTO cashierWithoutImageDTO);

    void saveEmployer(EmployerDTO employerDTO);

    EmployerDTO getEmployerById(long employerId);

    String updateEmployer(Long employerId, EmployerAllDetailsUpdateDTO cashierAllDetailsUpdateDTO);
}
