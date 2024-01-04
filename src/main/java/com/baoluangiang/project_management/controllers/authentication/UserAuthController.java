package com.baoluangiang.project_management.controllers.authentication;

import com.baoluangiang.project_management.controllers.utils.ResponseStatus;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.UserRegisterRequest;
import com.baoluangiang.project_management.models.payloads.UserRegisterResponse;
import com.baoluangiang.project_management.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user/auth")
public class UserAuthController {
    private final UserService userService;

    @Autowired
    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/register")
    public ResponseEntity<BaseResponse<UserRegisterResponse>> signup(@RequestBody UserRegisterRequest userRegisterRequest) {
        BaseResponse<UserRegisterResponse> response = userService.registerUser(userRegisterRequest);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }
}
