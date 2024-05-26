package com.lifepill.employeeService.controller;

import com.lifepill.employeeService.dto.EmployerBankDetailsDTO;
import com.lifepill.employeeService.dto.EmployerDTO;
import com.lifepill.employeeService.dto.EmployerWithBankDTO;
import com.lifepill.employeeService.dto.EmployerWithoutImageDTO;
import com.lifepill.employeeService.dto.request.EmployerAllDetailsUpdateDTO;
import com.lifepill.employeeService.dto.request.EmployerUpdateAccountDetailsDTO;
import com.lifepill.employeeService.dto.request.EmployerUpdateBankAccountDTO;
import com.lifepill.employeeService.exception.NotFoundException;
import com.lifepill.employeeService.service.EmployerService;
import com.lifepill.employeeService.util.StandardResponse;
import com.lifepill.employeeService.util.mappers.EmployerMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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