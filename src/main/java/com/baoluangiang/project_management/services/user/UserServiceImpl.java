package com.baoluangiang.project_management.services.user;

import com.baoluangiang.project_management.configurations.security.UserDetailsImpl;
import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.models.dtos.*;
import com.baoluangiang.project_management.models.payloads.*;
import com.baoluangiang.project_management.repositories.UserRepository;
import com.baoluangiang.project_management.services.information.InformationService;
import com.baoluangiang.project_management.services.phone.PhoneService;
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
    private final UserRepository userRepository;
    private final InformationService informationService;
    private final PhoneService phoneService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, InformationService informationService, PhoneService phoneService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.informationService = informationService;
        this.phoneService = phoneService;
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
    public BaseResponse<UserUpdateResponse> updateUser(Long userId, UserUpdateRequest updateInformation) {
        Optional<List<User>> userOptional = userRepository.findUserById(userId);
        User existingUser;
        if (userOptional.isEmpty()) {
            return BaseResponse.<UserUpdateResponse>builder()
                    .message("class: UserServiceImpl + func: updateUser(Long userId, UserDTO updatedInformation) + return 1")
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
        }

        existingUser = userOptional.get().get(0);

        // Create response for updater
        UserUpdateResponse updatedUserResponse = modelMapper.map(existingUser, UserUpdateResponse.class);

        // Update username
        BaseResponse<User> updatedUsername = updateUsername(updateInformation, existingUser);
        if(updatedUsername.getStatus() != HttpStatus.OK.value()) {
            return BaseResponse.<UserUpdateResponse>builder()
                    .message(updatedUsername.getMessage())
                    .status(updatedUsername.getStatus())
                    .build();
        }
        existingUser = updatedUsername.getData();
        updatedUserResponse.setUsername(existingUser.getUsername());

        // Update email
        BaseResponse<User> updatedEmail = updateEmail(updateInformation, existingUser);
        if(updatedEmail.getStatus() != HttpStatus.OK.value()) {
            return BaseResponse.<UserUpdateResponse>builder()
                    .message(updatedEmail.getMessage())
                    .status(updatedEmail.getStatus())
                    .build();
        }
        existingUser = updatedEmail.getData();
        updatedUserResponse.setEmail(existingUser.getEmail());

        // Update password
        BaseResponse<User> updatedPassword = updatePassword(updateInformation, existingUser);
        if(updatedPassword.getStatus() != HttpStatus.OK.value()) {
            return BaseResponse.<UserUpdateResponse>builder()
                    .message(updatedPassword.getMessage())
                    .status(updatedPassword.getStatus())
                    .build();
        }
        existingUser = updatedPassword.getData();

        // Update information
        InformationUpdateRequest informationUpdateRequest = updateInformation.getInformation();
        if(informationUpdateRequest != null){
            InformationUpdateResponse informationUpdateResponse = informationService.updateInformation(userId, informationUpdateRequest).getData();
            updatedUserResponse.setInformation(informationUpdateResponse);
        }

        // Update phone
        List<PhoneUpdateRequest> phonesUpdateRequest = updateInformation.getPhones();
        if(phonesUpdateRequest != null) {
            BaseResponse<List<PhoneUpdateResponse>> phonesUpdateResponse;
            phonesUpdateResponse = phoneService.updatePhone(userId, phonesUpdateRequest);
            if(phonesUpdateResponse.getData() == null){
                return BaseResponse.<UserUpdateResponse>builder()
                        .message(phonesUpdateResponse.getMessage())
                        .status(phonesUpdateResponse.getStatus())
                        .build();

            }
            updatedUserResponse.setPhones(phonesUpdateResponse.getData());
        } else {
            updatedUserResponse.setPhones(null);
        }

        // Save to database
        userRepository.save(existingUser);

        return BaseResponse.<UserUpdateResponse>builder()
                .message("class: UserServiceImpl + func: updateUser(Long userId, UserDTO updatedInformation) + return success")
                .data(updatedUserResponse)
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
                                .password(userEntity.getPassword())
                                .isActive(userEntity.isActive())
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

    public BaseResponse<User> updateUsername(UserUpdateRequest updateInformation, User existingUser){
        if(updateInformation.getUsername() == null) {
            return BaseResponse.<User>builder()
                    .status(HttpStatus.OK.value())
                    .data(existingUser)
                    .build();
        }

        // New username already exist
        if(userRepository.existsByUsername(updateInformation.getUsername())) {
            return BaseResponse.<User>builder()
                    .message("class: UserServiceImpl + func: updateUsername(UserUpdateRequest updateInformation, User existingUser) + return 1")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .data(existingUser)
                    .build();
        }

        // New username same with the old one
        if (updateInformation.getUsername().equals(existingUser.getUsername())) {
            return BaseResponse.<User>builder()
                    .message("class: UserServiceImpl + func: updateUsername(UserUpdateRequest updateInformation, User existingUser) + return 2")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .data(existingUser)
                    .build();
        }
        existingUser.setUsername(updateInformation.getUsername());
        return BaseResponse.<User>builder()
                .message("class: UserServiceImpl + func: updateUsername(UserUpdateRequest updateInformation, User existingUser) + return success")
                .status(HttpStatus.OK.value())
                .data(existingUser)
                .build();
    }

    public BaseResponse<User> updateEmail(UserUpdateRequest updateInformation, User existingUser){
        if(updateInformation.getEmail() == null) {
            return BaseResponse.<User>builder()
                    .status(HttpStatus.OK.value())
                    .data(existingUser)
                    .build();
        }

        // New email already exist
        if(userRepository.existsByEmail(updateInformation.getEmail())) {
            return BaseResponse.<User>builder()
                    .message("class: UserServiceImpl + func: updateEmail(Long userId, UserDTO updatedInformation) + return 1")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .data(existingUser)
                    .build();
        }

        // New email same with the old one
        if (updateInformation.getEmail().equals(existingUser.getEmail())) {
            return BaseResponse.<User>builder()
                    .message("class: UserServiceImpl + func: updateEmail(Long userId, UserDTO updatedInformation) + return 2")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .data(existingUser)
                    .build();
        }

        existingUser.setEmail(updateInformation.getEmail());
        return BaseResponse.<User>builder()
                .message("class: UserServiceImpl + func: updateEmail(Long userId, UserDTO updatedInformation) + return success")
                .status(HttpStatus.OK.value())
                .data(existingUser)
                .build();
    }

    public BaseResponse<User> updatePassword(UserUpdateRequest updateInformation, User existingUser){
        if(updateInformation.getOldPassword() == null) {
            return BaseResponse.<User>builder()
                    .status(HttpStatus.OK.value())
                    .data(existingUser)
                    .build();
        }

        // New password must not null
        if(updateInformation.getNewPassword() == null) {
            return BaseResponse.<User>builder()
                    .message("class: UserServiceImpl + func: updatePassword(Long userId, UserDTO updatedInformation) + return 1")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .data(existingUser)
                    .build();
        }

        // Wrong password
        if (!passwordEncoder.matches(updateInformation.getOldPassword(), existingUser.getPassword())) {
            return BaseResponse.<User>builder()
                    .message("class: UserServiceImpl + func: updatePassword(Long userId, UserDTO updatedInformation) + return 2")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .data(existingUser)
                    .build();
        }

        // Confirm new password not match
        if (!updateInformation.getNewPassword().equals(updateInformation.getConfirmNewPassword())) {
            return BaseResponse.<User>builder()
                    .message("class: UserServiceImpl + func: updatePassword(Long userId, UserDTO updatedInformation) + return 3")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .data(existingUser)
                    .build();
        }

        existingUser.setPassword(passwordEncoder.encode(updateInformation.getNewPassword()));
        return BaseResponse.<User>builder()
                .message("class: UserServiceImpl + func: updatePassword(Long userId, UserDTO updatedInformation) + return success")
                .status(HttpStatus.OK.value())
                .data(existingUser)
                .build();
    }
}
