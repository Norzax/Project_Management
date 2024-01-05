package com.baoluangiang.project_management.services.information;

import com.baoluangiang.project_management.entities.Information;
import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.models.dtos.InformationDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.InformationUpdateRequest;
import com.baoluangiang.project_management.models.payloads.InformationUpdateResponse;
import com.baoluangiang.project_management.repositories.InformationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InformationServiceImpl implements InformationService{
    private final InformationRepository informationRepository;
    private final ModelMapper modelMapper;

    @Override
    public BaseResponse<List<InformationDTO>> getAll() {
        return null;
    }

    @Override
    public BaseResponse<List<InformationDTO>> getById(Long userId) {
        return null;
    }

    @Override
    public BaseResponse<InformationUpdateResponse> updateInformation(Long userId, InformationUpdateRequest updateInformation) {
        Optional<Information> informationOptional = informationRepository.findInformationByUserId(userId);
        Information existingInformation;
        if (informationOptional.isPresent()) {
            existingInformation = informationOptional.get();
        } else {
            return BaseResponse.<InformationUpdateResponse>builder()
                    .message("class: InformationServiceImpl + func: updateInformation(Long userId, UserDTO updatedInformation) + return 1")
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
        }

        if(updateInformation != null) {
            // Update display name
            if (!updateInformation.getDisplayName().equals(existingInformation.getDisplayName()) &&
                    updateInformation.getDisplayName() != null) {
                existingInformation.setDisplayName(updateInformation.getDisplayName());
            }

            // Update bio
            if (!updateInformation.getBio().equals(existingInformation.getBio()) &&
                    updateInformation.getBio() != null) {
                existingInformation.setBio(updateInformation.getBio());
            }

            // Update birthday
            if (!updateInformation.getBirthday().equals(existingInformation.getBirthday()) &&
                    updateInformation.getBirthday() != null) {
                existingInformation.setBirthday(updateInformation.getBirthday());
            }

            // Update avatar url
            if (!updateInformation.getAvatarUrl().equals(existingInformation.getAvatarUrl()) &&
                    updateInformation.getAvatarUrl() != null) {
                existingInformation.setAvatarUrl(updateInformation.getAvatarUrl());
            }
        }

        InformationUpdateResponse updatedInformation = modelMapper.map(informationRepository.save(existingInformation), InformationUpdateResponse.class);
        return BaseResponse.<InformationUpdateResponse>builder()
                .message("class: UserServiceImpl + func: updateInformation(Long userId, InformationDTO updatedInformation) + return success")
                .data(updatedInformation)
                .status(HttpStatus.OK.value())
                .build();
    }

    @Override
    public BaseResponse<List<InformationDTO>> findUserByInformation(List<User> userList) {
        return null;
    }
}
