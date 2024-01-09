package com.baoluangiang.project_management.controllers.admin;

import com.baoluangiang.project_management.controllers.utils.ResponseStatus;
import com.baoluangiang.project_management.models.dtos.StatusDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.services.status.StatusService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/admin/statusManagement")
@AllArgsConstructor
public class StatusMngController {
    private final StatusService statusService;

    @GetMapping
    @RequestMapping("/all")
    public ResponseEntity<BaseResponse<List<StatusDTO>>> getAll(){
        BaseResponse<List<StatusDTO>> response = statusService.getAll();
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping
    @RequestMapping("/id/{id}")
    public ResponseEntity<BaseResponse<List<StatusDTO>>> getById(@PathVariable("id") Long statusId){
        BaseResponse<List<StatusDTO>> response = statusService.getById(statusId);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping
    @RequestMapping("/statusName/{statusName}")
    public ResponseEntity<BaseResponse<List<StatusDTO>>> getById(@PathVariable("statusName") String statusName){
        BaseResponse<List<StatusDTO>> response = statusService.getByStatusName(statusName);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }
}
