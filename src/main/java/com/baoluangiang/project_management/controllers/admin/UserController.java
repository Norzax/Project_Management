package com.baoluangiang.project_management.controllers.admin;

import com.baoluangiang.project_management.controllers.utils.ResponseStatus;
import com.baoluangiang.project_management.models.dtos.UserDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.UserUpdateRequest;
import com.baoluangiang.project_management.models.payloads.UserUpdateResponse;
import com.baoluangiang.project_management.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BaseResponse<List<UserDTO>>> findById(@PathVariable("id") Long userId) {
        BaseResponse<List<UserDTO>> response = userService.getById(userId);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<BaseResponse<List<UserDTO>>> findByUsername(@PathVariable("username") String username) {
        BaseResponse<List<UserDTO>> response = userService.getByUsername(username);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

//    @GetMapping("/{identifier}")
//    public ResponseEntity<BaseResponse<UserDTO>> findByIdOrUsername(@PathVariable("identifier") String identifier) {
//        try {
//            Long userId = Long.parseLong(identifier);
//            BaseResponse<UserDTO> response = userService.getById(userId);
//            HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
//            return ResponseEntity.status(httpStatus).body(response);
//        } catch (NumberFormatException e) {
//            BaseResponse<UserDTO> response = userService.getByUsername(identifier);
//            HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
//            return ResponseEntity.status(httpStatus).body(response);
//        }
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<UserUpdateResponse>> updateById(@PathVariable("id") Long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        BaseResponse<UserUpdateResponse> response = userService.updateUser(userId, userUpdateRequest);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }
}
