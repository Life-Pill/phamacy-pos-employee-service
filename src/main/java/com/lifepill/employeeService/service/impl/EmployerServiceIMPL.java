package com.lifepill.employeeService.service.impl;

import com.lifepill.employeeService.dto.EmployerBankDetailsDTO;
import com.lifepill.employeeService.dto.EmployerDTO;
import com.lifepill.employeeService.dto.EmployerWithBankDTO;
import com.lifepill.employeeService.dto.EmployerWithoutImageDTO;
import com.lifepill.employeeService.dto.request.*;
import com.lifepill.employeeService.entity.Employer;
import com.lifepill.employeeService.entity.EmployerBankDetails;
import com.lifepill.employeeService.entity.enums.Role;
import com.lifepill.employeeService.exception.EntityDuplicationException;
import com.lifepill.employeeService.exception.EntityNotFoundException;
import com.lifepill.employeeService.exception.NotFoundException;
import com.lifepill.employeeService.repository.EmployerBankDetailsRepository;
import com.lifepill.employeeService.repository.EmployerRepository;
import com.lifepill.employeeService.service.EmployerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EmployerServiceIMPL implements EmployerService {

}