package com.baoluangiang.project_management.services.user;

import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.models.dtos.UserDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.UserUpdateRequest;
import com.baoluangiang.project_management.models.payloads.UserUpdateResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    BaseResponse<Long> getLoggedInUserId();
    BaseResponse<UserDTO> getLoggedInUserInformation();
    BaseResponse<List<UserDTO>> getAll();
    BaseResponse<List<UserDTO>> getById(Long userId);
    BaseResponse<List<UserDTO>> getByUsername(String username);
    BaseResponse<UserUpdateResponse> updateUserInformation(Long userId, UserUpdateRequest updatedInformation);
    BaseResponse<Void> inactiveUser(Long userId);
    BaseResponse<Void> activeUser(String username);
    BaseResponse<UserDTO> registerUser(UserDTO registerInformation);
    BaseResponse<List<UserDTO>> findUserByInformation(List<User> userList);
}
