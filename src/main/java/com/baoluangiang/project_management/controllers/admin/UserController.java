package com.baoluangiang.project_management.controllers.admin;

import com.baoluangiang.project_management.models.dtos.UserDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<UserDTO>>> findAll() {
        BaseResponse<List<UserDTO>> response = userService.getAll();
        HttpStatus httpStatus;
        if (response.getStatus() == 404) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (response.getStatus() == 500){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            httpStatus = HttpStatus.OK;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }
}
