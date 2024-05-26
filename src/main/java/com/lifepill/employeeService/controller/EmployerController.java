package com.lifepill.employeeService.controller;

import com.lifepill.employeeService.service.EmployerService;
import com.lifepill.employeeService.util.mappers.EmployerMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing employer-related operations.
 */
@RestController
@RequestMapping("lifepill/v1/employers")
@AllArgsConstructor
public class EmployerController {

    private EmployerService employerService;
    private EmployerMapper employerMapper;

}