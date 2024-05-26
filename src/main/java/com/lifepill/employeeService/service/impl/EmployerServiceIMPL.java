package com.lifepill.employeeService.service.impl;

import com.lifepill.employeeService.dto.BranchDTO;
import com.lifepill.employeeService.dto.EmployerDTO;
import com.lifepill.employeeService.dto.EmployerWithoutImageDTO;
import com.lifepill.employeeService.entity.Employer;
import com.lifepill.employeeService.exception.EntityDuplicationException;
import com.lifepill.employeeService.exception.NotFoundException;
import com.lifepill.employeeService.repository.EmployerRepository;
import com.lifepill.employeeService.service.APIClient;
import com.lifepill.employeeService.service.EmployerService;
import com.lifepill.employeeService.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * EmployerServiceIMPL is a service class that implements the EmployerService interface.
 * It provides the functionality to manage employers in the system.
 */
@Service
@Transactional
@AllArgsConstructor
public class EmployerServiceIMPL implements EmployerService {

    private EmployerRepository employerRepository;
    private ModelMapper modelMapper;
    private APIClient apiClient;

    /**
     * This method is used to save an employer without an image.
     * It first checks if the employer already exists in the system by their id or email.
     * If the employer already exists, it throws an EntityDuplicationException.
     * If the employer does not exist, it retrieves the branch associated with the employer by its id.
     * If the branch does not exist, it throws a NotFoundException.
     * If the branch exists, it maps the EmployerWithoutImageDTO to an Employer entity and saves it in the database.
     * If the employer is not found after saving, it throws a NotFoundException.
     * If the employer is saved successfully, it returns a success message.
     *
     * @param employerWithoutImageDTO The employer data transfer object without an image.
     * @throws EntityDuplicationException If the employer already exists.
     * @throws NotFoundException          If the associated branch is not found.
     */
    @Override
    public void saveEmployerWithoutImage(EmployerWithoutImageDTO employerWithoutImageDTO) {
        // check if the employer already exists email or id
        if (employerRepository.existsById(employerWithoutImageDTO.getEmployerId())
                || employerRepository.existsAllByEmployerEmail(employerWithoutImageDTO.getEmployerEmail())) {
            throw new EntityDuplicationException("Employer already exists");
        } else {

            ResponseEntity<StandardResponse> standardResponseResponseEntity =
                    apiClient.getBranchById(employerWithoutImageDTO.getBranchId());

            if (standardResponseResponseEntity.getStatusCode() != HttpStatus.OK) {
                String errorMessage = standardResponseResponseEntity.getBody().getMessage();
                throw new NotFoundException(errorMessage);
            }

            BranchDTO branchDTO = modelMapper.map(
                    Objects.requireNonNull(standardResponseResponseEntity.getBody())
                            .getData(), BranchDTO.class
            );

            //check if the branch is existing
            assert branchDTO != null;
            if (branchDTO.getBranchId() == 0) {
                throw new NotFoundException("Branch not found for the given branch id:"
                        + employerWithoutImageDTO.getBranchId());
            }

            Employer employer = modelMapper.map(employerWithoutImageDTO, Employer.class);

            String savedEmployer = String.valueOf(employerRepository.findByEmployerEmail(employerWithoutImageDTO.getEmployerEmail()));
            if (savedEmployer != null) {
                employerRepository.save(employer);
                long employerId = employer.getEmployerId();
                employerWithoutImageDTO.setEmployerId(employerId);

            } else {
                throw new NotFoundException("Employer not found after saving");
            }
        }
    }

    /**
     * Saves an employer with image.
     *
     * @param employerDTO The employer data transfer object.
     * @throws EntityDuplicationException If the employer already exists.
     * @throws NotFoundException          If the associated branch is not found.
     */
    @Override
    public void saveEmployer(EmployerDTO employerDTO){
        // check if the cashier already exists email or id
        if (employerRepository.existsById(employerDTO.getEmployerId()) ||
                employerRepository.existsAllByEmployerEmail(employerDTO.getEmployerEmail())) {
            throw new EntityDuplicationException("Employer already exists");
        } else {
            // Retrieve the Branch entity by its ID
            ResponseEntity<StandardResponse> standardResponseResponseEntity =
                    apiClient.getBranchById(employerDTO.getBranchId());

            // Check if the branch exists
            if (standardResponseResponseEntity.getStatusCode() != HttpStatus.OK) {
                String errorMessage = standardResponseResponseEntity.getBody().getMessage();
                throw new NotFoundException(errorMessage);
            }
            //TODO: Check if the branch exists
            BranchDTO branchDTO = modelMapper.map(
                    Objects.requireNonNull(standardResponseResponseEntity.getBody())
                            .getData(), BranchDTO.class
            );
            // TODO: response employeeID get as 0. need to correct

            // Map EmployerDTO to Employer entity
            Employer employer = modelMapper.map(employerDTO, Employer.class);
            // Save the Employer entity
            employerRepository.save(employer);
        }
    }
}