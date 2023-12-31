package com.baoluangiang.project_management.services.status;

import com.baoluangiang.project_management.models.dtos.StatusDTO;
import com.baoluangiang.project_management.models.payloads.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusService {
    BaseResponse<List<StatusResponse>> getAll();
    BaseResponse<List<StatusResponse>> getById(Long statusId);
    BaseResponse<List<StatusResponse>> getByStatusName(String statusName);
    BaseResponse<StatusResponse> create(StatusRequest newStatus);
    BaseResponse<StatusResponse> update(Long statusId, StatusRequest statusUpdateRequest);
    BaseResponse<Void> deleteStatus(Long userId);
}
