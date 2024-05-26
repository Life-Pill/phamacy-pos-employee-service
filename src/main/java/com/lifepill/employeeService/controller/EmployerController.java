package com.lifepill.employeeService.controller;

import com.lifepill.employeeService.dto.EmployerDTO;
import com.lifepill.employeeService.dto.EmployerWithoutImageDTO;
import com.lifepill.employeeService.service.EmployerService;
import com.lifepill.employeeService.util.StandardResponse;
import com.lifepill.employeeService.util.mappers.EmployerMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller class for managing employer-related operations.
 */
@RestController
@RequestMapping("lifepill/v1/employers")
@AllArgsConstructor
public class EmployerController {

    private EmployerService employerService;
    private EmployerMapper employerMapper;


    /**
     * Saves an employer without an image.
     *
     * @param cashierWithoutImageDTO DTO containing details of the employer without image.
     * @return A string indicating the success of the operation.
     */
    @PostMapping("/save-without-image")
    public ResponseEntity<StandardResponse> saveCashierWithoutImage(@RequestBody EmployerWithoutImageDTO cashierWithoutImageDTO) {
        employerService.saveEmployerWithoutImage(cashierWithoutImageDTO);

        return new ResponseEntity<>(
                new StandardResponse(201, "successfully saved", cashierWithoutImageDTO),
                HttpStatus.CREATED
        );
    } /**
     * Saves an employer with an image.
     *
     * @param employerDTO DTO containing details of the employer including image.
     * @param file        MultipartFile representing the image file.
     * @return A string indicating the success of the operation.
     * @throws IOException If an I/O error occurs.
     */
    @PostMapping("/save-with-image")
    public ResponseEntity<StandardResponse> saveEmployerWithImage(
            @ModelAttribute EmployerDTO employerDTO,
            @RequestParam("file") MultipartFile file
    )
            throws IOException {
        // Check if a file is provided
        if (!file.isEmpty()) {
            // Convert MultipartFile to byte array
            byte[] profileImage = file.getBytes();
            // Set the profile image in the employerDTO
            employerDTO.setProfileImage(profileImage);
        }
        // Save the cashier along with the profile photo
        employerService.saveEmployer(employerDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "successfully saved", employerDTO),
                HttpStatus.CREATED
        );
    }
}