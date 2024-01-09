package com.baoluangiang.project_management.services.status;

import com.baoluangiang.project_management.models.dtos.StatusDTO;
import com.baoluangiang.project_management.models.payloads.*;

import java.util.List;

public interface StatusService {
    BaseResponse<List<StatusDTO>> getAll();
    BaseResponse<List<StatusDTO>> getById(Long statusId);
    BaseResponse<List<StatusDTO>> getByStatusName(String statusName);
    BaseResponse<StatusDTO> updateStatus(Long statusId, StatusUpdateRequest updatedInformation);
    BaseResponse<Void> deleteStatus(Long userId);
}
