package com.baoluangiang.project_management.controllers.authentication;

import com.baoluangiang.project_management.controllers.utils.ResponseStatus;
import com.baoluangiang.project_management.models.payloads.*;
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

    @PutMapping("/signup")
    public ResponseEntity<BaseResponse<UserRegisterResponse>> signup(@RequestBody UserRegisterRequest userRegisterRequest) {
        BaseResponse<UserRegisterResponse> response = userService.registerUser(userRegisterRequest);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse<UserLoginResponse>> signin(@RequestBody UserLoginRequest userLoginRequest) {
        BaseResponse<UserLoginResponse> response = userService.login(userLoginRequest);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }
}
