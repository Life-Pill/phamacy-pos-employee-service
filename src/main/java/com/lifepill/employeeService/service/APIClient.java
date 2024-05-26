package com.lifepill.employeeService.service;

import com.lifepill.employeeService.dto.BranchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "BRANCH-SERVICE/lifepill/v1")
public interface APIClient {


    @GetMapping(path = "branch/get-branch-by-id", params = "branchId")
    @Transactional
    BranchDTO getBranchById(
            @RequestParam(value = "id") int branchId
    );

}
