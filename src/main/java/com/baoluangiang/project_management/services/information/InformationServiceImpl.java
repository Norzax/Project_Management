package com.baoluangiang.project_management.services.information;

import com.baoluangiang.project_management.entities.Information;
import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.models.dtos.InformationDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.InformationUpdateRequest;
import com.baoluangiang.project_management.models.payloads.InformationUpdateResponse;
import com.baoluangiang.project_management.repositories.InformationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationServiceImpl implements InformationService{
    private final InformationRepository informationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InformationServiceImpl(InformationRepository informationRepository, ModelMapper modelMapper) {
        this.informationRepository = informationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BaseResponse<List<InformationDTO>> getAll() {
        return null;
    }

    @Override
    public BaseResponse<List<InformationDTO>> getById(Long userId) {
        return null;
    }

    @Override
    public BaseResponse<InformationUpdateResponse> updateUserInformation(Long userId, InformationUpdateRequest updatedInformation) {
        Optional<Information> informationOptional = informationRepository.findInformationByUserId(userId);
        if (informationOptional.isEmpty()) {
            return BaseResponse.<InformationUpdateResponse>builder()
                    .message("class: InformationServiceImpl + func: updateUserInformation(Long userId, UserDTO updatedInformation) + return 1")
                    .data(null)
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
        }

        InformationDTO existingInformation = modelMapper.map(informationOptional.get(), InformationDTO.class);
        if(!updatedInformation.getBio().equals(existingInformation.getBio()) &&
                updatedInformation.getBio() != null) {
            existingInformation.setBio(updatedInformation.getBio());
        } else if(!updatedInformation.getBirthday().equals(existingInformation.getBirthday()) &&
                updatedInformation.getBirthday() != null) {
            existingInformation.setBirthday(updatedInformation.getBirthday());
        } else if(!updatedInformation.getAvatarUrl().equals(existingInformation.getAvatarUrl()) &&
                updatedInformation.getAvatarUrl() != null) {
            existingInformation.setAvatarUrl(updatedInformation.getAvatarUrl());
        } else if(!updatedInformation.getDisplayName().equals(existingInformation.getDisplayName()) &&
                updatedInformation.getDisplayName() != null) {
            existingInformation.setDisplayName(updatedInformation.getDisplayName());
        }

        informationRepository.save(modelMapper.map(existingInformation, Information.class));
        return BaseResponse.<InformationUpdateResponse>builder()
                .message("class: UserServiceImpl + func: updateUserInformation(Long userId, InformationDTO updatedInformation) + return 2")
                .data(modelMapper.map(existingInformation, InformationUpdateResponse.class))
                .status(HttpStatus.OK.value())
                .build();
    }

    @Override
    public BaseResponse<List<InformationDTO>> findUserByInformation(List<User> userList) {
        return null;
    }
}
