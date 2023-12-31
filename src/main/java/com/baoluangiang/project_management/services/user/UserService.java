package com.baoluangiang.project_management.services.user;

import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.models.dtos.UserDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    BaseResponse<Long> getLoggedInUserId();
    BaseResponse<UserDTO> getLoggedInUserInformation();
    BaseResponse<List<UserDTO>> getAll();
    BaseResponse<UserDTO> getById(Long userId);
    BaseResponse<UserDTO> getByUsername(String username);
    BaseResponse<UserDTO> updateUserInformation(UserDTO updatedInformation);
    BaseResponse<Void> inactiveUser(Long userId);
    BaseResponse<Void> activeUser(String username);
    BaseResponse<UserDTO> registerUser(UserDTO registerInformation);
    BaseResponse<UserDTO> findUserByInformation(Optional<User> foundUser);
}
