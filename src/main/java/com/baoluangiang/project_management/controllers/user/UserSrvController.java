package com.baoluangiang.project_management.controllers.user;

import com.baoluangiang.project_management.controllers.utils.ResponseStatus;
import com.baoluangiang.project_management.models.dtos.UserDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.UserUpdateRequest;
import com.baoluangiang.project_management.models.payloads.UserUpdateResponse;
import com.baoluangiang.project_management.services.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/user/account")
@AllArgsConstructor
public class UserSrvController {
    private final UserService userService;

    @GetMapping("/info")
    public ResponseEntity<BaseResponse<List<UserDTO>>> getInfo(){
        BaseResponse<List<UserDTO>> response = userService.getById(userService.getLoggedInUserId().getData());
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PutMapping("/updateInfo")
    public ResponseEntity<BaseResponse<UserUpdateResponse>> updateInfo(@RequestBody UserUpdateRequest userUpdateRequest){
        BaseResponse<UserUpdateResponse> response = userService.updateUser(userService.getLoggedInUserId().getData(), userUpdateRequest);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PutMapping("/inactiveAccount")
    public ResponseEntity<BaseResponse<Void>> inactiveAccount(){
        BaseResponse<Void> response = userService.inactiveUser(userService.getLoggedInUserId().getData());
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PutMapping("/reactiveAccount/{username}")
    public ResponseEntity<BaseResponse<Void>> reactiveAccount(@PathVariable("username") String username){
        BaseResponse<Void> response = userService.activeUser(username);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }
}
