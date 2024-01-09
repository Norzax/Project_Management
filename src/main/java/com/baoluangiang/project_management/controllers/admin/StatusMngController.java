package com.baoluangiang.project_management.controllers.admin;

import com.baoluangiang.project_management.controllers.utils.ResponseStatus;
import com.baoluangiang.project_management.models.dtos.StatusDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.StatusResponse;
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

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<StatusResponse>>> getAll(){
        BaseResponse<List<StatusResponse>> response = statusService.getAll();
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BaseResponse<List<StatusResponse>>> getById(@PathVariable("id") Long statusId){
        BaseResponse<List<StatusResponse>> response = statusService.getById(statusId);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/statusName/{statusName}")
    public ResponseEntity<BaseResponse<List<StatusResponse>>> getById(@PathVariable("statusName") String statusName){
        BaseResponse<List<StatusResponse>> response = statusService.getByStatusName(statusName);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }
}
