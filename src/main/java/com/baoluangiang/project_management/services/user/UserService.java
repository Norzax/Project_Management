package com.baoluangiang.project_management.services.user;

import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.models.dtos.UserDTO;
import com.baoluangiang.project_management.models.payloads.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    BaseResponse<Long> getLoggedInUserId();
    BaseResponse<UserDTO> getLoggedInUserInformation();
    BaseResponse<List<UserDTO>> getAll();
    BaseResponse<List<UserDTO>> getById(Long userId);
    BaseResponse<List<UserDTO>> getByUsername(String username);
    BaseResponse<UserUpdateResponse> updateUser(Long userId, UserUpdateRequest updatedInformation);
    BaseResponse<Void> inactiveUser(Long userId);
    BaseResponse<Void> activeUser(String username);
    BaseResponse<UserRegisterResponse> registerUser(UserRegisterRequest registerInformation);
    BaseResponse<UserLoginResponse> login(UserLoginRequest userLoginRequest);
}
