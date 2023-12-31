package com.baoluangiang.project_management.services.user;

import com.baoluangiang.project_management.models.dtos.UserDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;

import java.util.List;

public interface UserService {
    BaseResponse<Long> getLoggedInUserId();
    BaseResponse<UserDTO> getLoggedInUserInformation();
    BaseResponse<List<UserDTO>> getAll();
    BaseResponse<UserDTO> getById();
    BaseResponse<UserDTO> getByUsername();
    BaseResponse<UserDTO> updateUserInformation(UserDTO updatedInformation);
    BaseResponse<?> inActiveUser(Long userId);
    BaseResponse<?> activeUser(String username);
}
