package com.baoluangiang.project_management.services.phone;

import com.baoluangiang.project_management.models.dtos.PhoneDTO;
import com.baoluangiang.project_management.models.payloads.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhoneService {
    BaseResponse<List<PhoneDTO>> getAll();
    BaseResponse<List<PhoneDTO>> getById(Long userId);
    BaseResponse<List<PhoneUpdateResponse>> updatePhone(Long userId, List<PhoneUpdateRequest> updatedPhone);
}
