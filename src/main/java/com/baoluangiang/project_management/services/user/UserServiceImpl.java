package com.baoluangiang.project_management.services.user;

import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.models.dtos.PhoneDTOForUserDTO;
import com.baoluangiang.project_management.models.dtos.UserDTO;
import com.baoluangiang.project_management.models.dtos.UserDetailsDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BaseResponse<Long> getLoggedInUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetailsDTO userDetails) {
                return BaseResponse.<Long>builder()
                        .status(HttpStatus.OK.value())
                        .data(userDetails.getUser().getId())
                        .message("class: UserServiceImpl + func: getLoggedInUserId() + return 1")
                        .build();
            } else {
                return BaseResponse.<Long>builder()
                        .status(HttpStatus.OK.value())
                        .data(null)
                        .message("class: UserServiceImpl + func: getLoggedInUserId() + return 2")
                        .build();
            }
        } catch (Exception e) {
            return BaseResponse.<Long>builder()
                    .status(HttpStatus.OK.value())
                    .data(null)
                    .message("class: UserServiceImpl + func: getLoggedInUserId() + return 3")
                    .build();
        }
    }

    @Override
    public BaseResponse<UserDTO> getLoggedInUserInformation() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetailsDTO userDetails) {
                return BaseResponse.<UserDTO>builder()
                        .status(HttpStatus.OK.value())
                        .data(modelMapper.map(userDetails.getUser(), UserDTO.class))
                        .message("class: UserServiceImpl + func: getLoggedInUserInformation() + return 1")
                        .build();
            } else {
                return BaseResponse.<UserDTO>builder()
                        .status(HttpStatus.OK.value())
                        .data(null)
                        .message("class: UserServiceImpl + func: getLoggedInUserInformation() + return 2")
                        .build();
            }
        } catch (Exception e) {
            return BaseResponse.<UserDTO>builder()
                    .status(HttpStatus.OK.value())
                    .data(null)
                    .message("class: UserServiceImpl + func: getLoggedInUserInformation() + return 3")
                    .build();
        }
    }

    @Override
    public BaseResponse<List<UserDTO>> getAll() {
        try {
            List<User> list = userRepository.findAll();

            if (list.isEmpty()) {
                return BaseResponse.<List<UserDTO>>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("class: UserServiceImpl + func: getAll() + return 1")
                        .build();
            }

            List<UserDTO> listRes = list.stream()
                    .map(userEntity -> {
                        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
                        List<PhoneDTOForUserDTO> listPhone = userEntity.getPhones().stream()
                                .map(phoneEntity -> modelMapper.map(phoneEntity, PhoneDTOForUserDTO.class)).toList();
                        userDTO.setPhones(listPhone);
                        return userDTO;
                    }).toList();

            return BaseResponse.<List<UserDTO>>builder()
                    .status(HttpStatus.OK.value())
                    .message("class: UserServiceImpl + func: getAll() + return 2")
                    .data(listRes)
                    .build();

        } catch (Exception e) {
            return BaseResponse.<List<UserDTO>>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .message("class: UserServiceImpl + func: getAll() + return 3")
                    .build();
        }
    }

    @Override
    public BaseResponse<UserDTO> getById(Long userId) {
        try {
            Optional<User> foundUser = userRepository.findById(userId);

            if (foundUser.isEmpty()) {
                return BaseResponse.<UserDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("class: UserServiceImpl + func: getById() + return 1")
                        .data(null)
                        .build();
            }

            return BaseResponse.<UserDTO>builder()
                    .status(HttpStatus.OK.value())
                    .message("class: UserServiceImpl + func: getById() + return 2")
                    .data(modelMapper.map(foundUser, UserDTO.class))
                    .build();
        } catch (Exception e) {
            return BaseResponse.<UserDTO>builder()
                    .status(HttpStatus.OK.value())
                    .data(null)
                    .message("class: UserServiceImpl + func: getById() + return 3")
                    .build();
        }
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

    @Override
    public BaseResponse<UserDTO> registerUser(UserDTO registerInformation) {
        return null;
    }
}
