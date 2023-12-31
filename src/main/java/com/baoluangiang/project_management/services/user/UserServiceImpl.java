package com.baoluangiang.project_management.services.user;

import com.baoluangiang.project_management.models.dtos.UserDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BaseResponse<Long> getLoggedInUserId() {
        return null;
    }

    @Override
    public BaseResponse<UserDTO> getLoggedInUserInformation() {
        return null;
    }

    @Override
    public BaseResponse<List<UserDTO>> getAll() {
        return null;
    }

    @Override
    public BaseResponse<UserDTO> getById() {
        return null;
    }

    @Override
    public BaseResponse<UserDTO> getByUsername() {
        return null;
    }

    @Override
    public BaseResponse<UserDTO> updateUserInformation(UserDTO updatedInformation) {
        return null;
    }

    @Override
    public BaseResponse<?> inActiveUser(Long userId) {
        return null;
    }

    @Override
    public BaseResponse<?> activeUser(String username) {
        return null;
    }
}
