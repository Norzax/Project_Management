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
    private final InformationService informationService;
    private final PhoneService phoneService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(InformationService informationService, PhoneService phoneService, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.informationService = informationService;
        this.phoneService = phoneService;
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
    public BaseResponse<UserUpdateResponse> updateUser(Long userId, UserUpdateRequest updateInformation) {
        Optional<List<User>> userOptional = userRepository.findUserById(userId);
        User existingUser;
        if (!userOptional.isPresent()) {
            return BaseResponse.<UserUpdateResponse>builder()
                    .message("class: UserServiceImpl + func: updateUser(Long userId, UserDTO updatedInformation) + return 1")
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
        }

        existingUser = userOptional.get().get(0);

        // Update username
        if(updateInformation.getUsername() != null){
            if(!userRepository.existsByUsername(updateInformation.getUsername()) && !updateInformation.getUsername().equals(existingUser.getUsername())) {
                existingUser.setUsername(updateInformation.getUsername());
            } else if(userRepository.existsByUsername(updateInformation.getUsername())) {       // New username already exist
                return BaseResponse.<UserUpdateResponse>builder()
                        .message("class: UserServiceImpl + func: updateUser(Long userId, UserDTO updatedInformation) + return 2")
                        .status(HttpStatus.OK.value())
                        .build();
            } else if (updateInformation.getUsername().equals(existingUser.getUsername())) {    // New username same with the old one
                return BaseResponse.<UserUpdateResponse>builder()
                        .message("class: UserServiceImpl + func: updateUser(Long userId, UserDTO updatedInformation) + return 3")
                        .status(HttpStatus.OK.value())
                        .build();
            }
        }

        // Update password
        if(updateInformation.getOldPassword() != null) {
            if(updateInformation.getNewPassword() != null &&
                    passwordEncoder.matches(updateInformation.getOldPassword(), existingUser.getPassword()) &&
                    updateInformation.getNewPassword().equals(updateInformation.getConfirmNewPassword())) {
                existingUser.setPassword(passwordEncoder.encode(updateInformation.getNewPassword()));
            } else if (!passwordEncoder.matches(updateInformation.getOldPassword(), existingUser.getPassword())) {      // Wrong password
                return BaseResponse.<UserUpdateResponse>builder()
                        .message("class: UserServiceImpl + func: updateUser(Long userId, UserDTO updatedInformation) + return 4")
                        .status(HttpStatus.OK.value())
                        .build();
            } else if (!updateInformation.getNewPassword().equals(updateInformation.getConfirmNewPassword())) {          // Confirm new password not match
                return BaseResponse.<UserUpdateResponse>builder()
                        .message("class: UserServiceImpl + func: updateUser(Long userId, UserDTO updatedInformation) + return 5")
                        .status(HttpStatus.OK.value())
                        .build();
            }
        }

        // Update information
        InformationUpdateRequest informationUpdateRequest = updateInformation.getInformation();
        InformationUpdateResponse informationUpdateResponse = null;
        if(informationUpdateRequest != null){
            informationUpdateResponse = informationService.updateInformation(userId, informationUpdateRequest).getData();
        }

        // Update phone
        List<PhoneUpdateRequest> phonesUpdateRequest = updateInformation.getPhones();
        List<PhoneUpdateResponse> phonesUpdateResponse = null;
        if(phonesUpdateRequest != null) {
            phonesUpdateResponse = phoneService.updatePhone(userId, phonesUpdateRequest).getData();
        }

        // Save to database
        userRepository.save(existingUser);

        // Create response for updater
        UserUpdateResponse updatedUserResponse = modelMapper.map(existingUser, UserUpdateResponse.class);
        updatedUserResponse.setInformation(informationUpdateResponse);
        updatedUserResponse.setPhones(phonesUpdateResponse);

        return BaseResponse.<UserUpdateResponse>builder()
                .message("class: UserServiceImpl + func: updateUser(Long userId, UserDTO updatedInformation) + return success ")
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
}
