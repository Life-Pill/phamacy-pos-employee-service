package com.lifepill.employeeService.service;

import com.lifepill.employeeService.dto.BranchDTO;
import com.lifepill.employeeService.dto.EmployerWithoutImageDTO;
import com.lifepill.employeeService.entity.Employer;
import com.lifepill.employeeService.exception.EntityDuplicationException;
import com.lifepill.employeeService.exception.NotFoundException;
import com.lifepill.employeeService.repository.EmployerRepository;
import com.lifepill.employeeService.service.APIClient;
import com.lifepill.employeeService.service.impl.EmployerServiceIMPL;
import com.lifepill.employeeService.util.StandardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployerServiceIMPLTest {

    @Mock
    private EmployerRepository employerRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private APIClient apiClient;

    @InjectMocks
    private EmployerServiceIMPL employerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEmployerWithoutImage_WhenEmployerExists_ThrowsEntityDuplicationException() {
        EmployerWithoutImageDTO employerWithoutImageDTO = new EmployerWithoutImageDTO();
        employerWithoutImageDTO.setEmployerId(1);
        employerWithoutImageDTO.setEmployerEmail("test@test.com");

        when(employerRepository.existsById(employerWithoutImageDTO.getEmployerId())).thenReturn(true);

        assertThrows(EntityDuplicationException.class, () -> employerService.saveEmployerWithoutImage(employerWithoutImageDTO));
    }


}