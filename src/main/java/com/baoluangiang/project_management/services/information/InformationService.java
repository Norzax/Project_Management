package com.baoluangiang.project_management.services.information;

import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.models.dtos.InformationDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.InformationUpdateRequest;
import com.baoluangiang.project_management.models.payloads.InformationUpdateResponse;
import com.baoluangiang.project_management.models.payloads.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InformationService {
    BaseResponse<List<InformationDTO>> getAll();
    BaseResponse<List<InformationDTO>> getById(Long userId);
    BaseResponse<InformationUpdateResponse> updateUserInformation(Long userId, InformationUpdateRequest updatedInformation);
    BaseResponse<List<InformationDTO>> findUserByInformation(List<User> userList);
}
