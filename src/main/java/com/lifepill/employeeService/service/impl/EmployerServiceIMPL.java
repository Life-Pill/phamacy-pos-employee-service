package com.lifepill.employeeService.service.impl;

import com.lifepill.employeeService.dto.BranchDTO;
import com.lifepill.employeeService.dto.EmployerWithoutImageDTO;
import com.lifepill.employeeService.entity.Employer;
import com.lifepill.employeeService.exception.EntityDuplicationException;
import com.lifepill.employeeService.exception.NotFoundException;
import com.lifepill.employeeService.repository.EmployerRepository;
import com.lifepill.employeeService.service.APIClient;
import com.lifepill.employeeService.service.EmployerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class EmployerServiceIMPL implements EmployerService {

    private EmployerRepository employerRepository;
    private ModelMapper modelMapper;
    private APIClient apiClient;


    /**
     * Saves an employer without image.
     *
     * @param employerWithoutImageDTO The employer data transfer object without an image.
     * @return A message indicating the success of the operation.
     * @throws EntityDuplicationException If the employer already exists.
     * @throws NotFoundException          If the associated branch is not found.
     */
    @Override
    public String saveEmployerWithoutImage(EmployerWithoutImageDTO employerWithoutImageDTO) {
        // check if the employer already exists email or id
        if (employerRepository.existsById(employerWithoutImageDTO.getEmployerId())
                || employerRepository.existsAllByEmployerEmail(employerWithoutImageDTO.getEmployerEmail())) {
            throw new EntityDuplicationException("Employer already exists");
        } else {

            // Retrieve the Branch entity by its ID
//            Branch branch = branchRepository.findById(employerWithoutImageDTO.getBranchId())
//                    .orElseThrow(() -> new NotFoundException("Branch not found with ID: " + employerWithoutImageDTO.getBranchId()));

            BranchDTO branchDTO = apiClient.getBranchById((int)employerWithoutImageDTO.getBranchId());

            System.out.println(branchDTO);

            // Map EmployerDTO to Employer entity
            Employer employer = modelMapper.map(employerWithoutImageDTO, Employer.class);

            // Set the Branch entity to the Employer
          //  employer.setBranch(branch);

            String savedEmployer = String.valueOf(employerRepository.findByEmployerEmail(employerWithoutImageDTO.getEmployerEmail()));
            if (savedEmployer != null) {
                employerRepository.save(employer);
                long employerId = employer.getEmployerId();
                employerWithoutImageDTO.setEmployerId(employerId);

            } else {
                throw new NotFoundException("Employer not found after saving");
            }
            return "Employer Saved";
        }
    }
}