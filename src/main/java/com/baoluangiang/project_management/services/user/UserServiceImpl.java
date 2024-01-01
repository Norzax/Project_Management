package com.baoluangiang.project_management.services.user;

import com.baoluangiang.project_management.configurations.security.UserDetailsImpl;
import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.models.dtos.*;
import com.baoluangiang.project_management.models.payloads.*;
import com.baoluangiang.project_management.repositories.UserRepository;
import com.baoluangiang.project_management.services.information.InformationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    private final InformationService informationService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(InformationService informationService, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.informationService = informationService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public BaseResponse<Long> getLoggedInUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {
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

            if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {
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

    public BaseResponse<List<UserDTO>> getAll() {
        Optional<List<User>> userListOptional = userRepository.findAllUser();
        if (userListOptional.isPresent()) {
            return findUserByInformation(userListOptional.get());
        } else {
            return BaseResponse.<List<UserDTO>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: UserServiceImpl + func: getAll() + return 1")
                    .build();
        }
    }

    public BaseResponse<List<UserDTO>> getById(Long userId) {
        Optional<List<User>> userOptional = userRepository.findUserById(userId);
        if (userOptional.isPresent()) {
            return findUserByInformation(userOptional.get());
        } else {
            return BaseResponse.<List<UserDTO>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: UserServiceImpl + func: getById() + return 1")
                    .build();
        }
    }

    public BaseResponse<List<UserDTO>> getByUsername(String username) {
        Optional<List<User>> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            return findUserByInformation(userOptional.get());
        } else {
            return BaseResponse.<List<UserDTO>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: UserServiceImpl + func: getByUsername() + return 1")
                    .build();
        }
    }

    @Override
    public BaseResponse<UserUpdateResponse> updateUserInformation(Long userId, UserUpdateRequest updatedInformation) {
        Optional<List<User>> userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            return BaseResponse.<UserUpdateResponse>builder()
                    .message("class: UserServiceImpl + func: updateUserInformation(Long userId, UserDTO updatedInformation) + return 1")
                    .data(null)
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
        }

        List<UserDTO> existingUser = findUserByInformation(userOptional.get()).getData();
        if (!existingUser.get(0).isActive()) {
            return BaseResponse.<UserUpdateResponse>builder()
                    .message("class: UserServiceImpl + func: updateUserInformation(Long userId, UserDTO updatedInformation) + return 2")
                    .data(modelMapper.map(existingUser.get(0), UserUpdateResponse.class))
                    .status(HttpStatus.FORBIDDEN.value())
                    .build();
        }

        // Update username
        if(updatedInformation.getUsername() != null &&
           !userRepository.existsByUsername(updatedInformation.getUsername()) &&
           !updatedInformation.getUsername().equals(existingUser.get(0).getUsername())){
            existingUser.get(0).setUsername(updatedInformation.getUsername());
        } else if (updatedInformation.getOldPassword() != null && updatedInformation.getNewPassword() != null) {
            // Update password
            // New password must not be the same with old password
            if (!passwordEncoder.matches(updatedInformation.getOldPassword(), existingUser.get(0).getPassword())) {
                if(!updatedInformation.getNewPassword().equals(updatedInformation.getConfirmNewPassword())){
                    // New password must be the same with confirm new password
                    return BaseResponse.<UserUpdateResponse>builder()
                            .message("class: UserServiceImpl + func: updateUserInformation(Long userId, UserDTO updatedInformation) + return 3")
                            .data(modelMapper.map(existingUser.get(0), UserUpdateResponse.class))
                            .status(HttpStatus.BAD_REQUEST.value())
                            .build();
                } else {
                    String encodedPassword = passwordEncoder.encode(updatedInformation.getNewPassword());
                    existingUser.get(0).setPassword(encodedPassword);
                }
            } else {
                return BaseResponse.<UserUpdateResponse>builder()
                        .message("class: UserServiceImpl + func: updateUserInformation(Long userId, UserDTO updatedInformation) + return 4")
                        .data(modelMapper.map(existingUser.get(0), UserUpdateResponse.class))
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build();
            }
        } else if(updatedInformation.getInformation() != null){
            // Update information
            InformationUpdateResponse newInformation = informationService.updateUserInformation(userId, modelMapper.map(updatedInformation.getInformation(), InformationUpdateRequest.class)).getData();
            existingUser.get(0).setInformation(modelMapper.map(newInformation, InformationDTO.class));

        } else if(!updatedInformation.getPhones().isEmpty()){
            // Update phone list
        }

        userRepository.save(modelMapper.map(existingUser.get(0), User.class));

        return BaseResponse.<UserUpdateResponse>builder()
                .message("class: UserServiceImpl + func: updateUserInformation(Long userId, UserDTO updatedInformation) + return 5")
                .data(modelMapper.map(existingUser.get(0), UserUpdateResponse.class))
                .status(HttpStatus.OK.value())
                .build();
    }

    @Override
    public BaseResponse<Void> inactiveUser(Long userId) {
        Optional<List<User>> userOptional = userRepository.findUserById(userId);
        if (userOptional.isPresent()) {
            List<UserDTO> inactiveUser = findUserByInformation(userOptional.get()).getData();
            if (inactiveUser.get(0).isActive()) {
                User setInactiveUser = modelMapper.map(inactiveUser, User.class);
                setInactiveUser.setActive(false);
                userRepository.save(setInactiveUser);
                return BaseResponse.<Void>builder()
                        .message("class: UserServiceImpl + func: inactiveUser(Long userId) + return 1")
                        .data(null)
                        .status(HttpStatus.OK.value()).build();
            }
            return BaseResponse.<Void>builder()
                    // already inactive
                    .message("class: UserServiceImpl + func: inactiveUser(Long userId) + return 2")
                    .data(null)
                    .status(HttpStatus.OK.value()).build();
        }
        return BaseResponse.<Void>builder()
                .message("class: UserServiceImpl + func: inactiveUser(Long userId) + return 3")
                .data(null)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse<Void> activeUser(String username) {
        Optional<List<User>> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            List<UserDTO> inactiveUser = findUserByInformation(userOptional.get()).getData();
            if (!inactiveUser.get(0).isActive()) {
                User setActiveUser = modelMapper.map(inactiveUser, User.class);
                setActiveUser.setActive(true);
                userRepository.save(setActiveUser);
                return BaseResponse.<Void>builder()
                        .message("class: UserServiceImpl + func: activeUser(String username) + return 1")
                        .data(null)
                        .status(HttpStatus.OK.value()).build();
            }
            return BaseResponse.<Void>builder()
                    // already active
                    .message("class: UserServiceImpl + func: activeUser(String username) + return 2")
                    .data(null)
                    .status(HttpStatus.OK.value()).build();
        }
        return BaseResponse.<Void>builder()
                .message("class: UserServiceImpl + func: activeUser(String username) + return 3")
                .data(null)
                .status(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse<UserDTO> registerUser(UserDTO registerInformation) {
        return null;
    }

    public BaseResponse<List<UserDTO>> findUserByInformation(List<User> userList){
        try {
            List<UserDTO> userDTOList = userList.stream()
                    .map(userEntity -> {
                        List<PhoneDTO> phoneDTOList = userEntity.getPhones().stream()
                                .map(phoneEntity -> {
                                    PhoneDTO phoneDTO = modelMapper.map(phoneEntity, PhoneDTO.class);
                                    phoneDTO.setId(null);
                                    phoneDTO.setUser(null);
                                    return phoneDTO;
                                })
                                .toList();

                        InformationDTO informationDTO = modelMapper.map(userEntity.getInformation(), InformationDTO.class);
                        informationDTO.setUser(null);
                        informationDTO.setId(null);

                        return UserDTO.builder()
                                .id(userEntity.getId())
                                .username(userEntity.getUsername())
                                .information(informationDTO)
                                .phones(phoneDTOList)
                                .build();
                    }).toList();

            return BaseResponse.<List<UserDTO>>builder()
                    .status(HttpStatus.OK.value())
                    .message("class: UserServiceImpl + func: getById() + return 1")
                    .data(userDTOList)
                    .build();

        } catch (Exception e) {
            return BaseResponse.<List<UserDTO>>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .message("class: UserServiceImpl + func: getById() + return 2")
                    .build();
        }
    }
}
