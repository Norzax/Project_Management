package com.baoluangiang.project_management.services.status;

import com.baoluangiang.project_management.entities.Currency;
import com.baoluangiang.project_management.entities.Status;
import com.baoluangiang.project_management.models.dtos.StatusDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.CurrencyResponse;
import com.baoluangiang.project_management.models.payloads.StatusUpdateRequest;
import com.baoluangiang.project_management.repositories.CurrencyRepository;
import com.baoluangiang.project_management.repositories.StatusRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService{
    private final StatusRepository statusRepository;
    private final ModelMapper modelMapper;

    @Override
    public BaseResponse<List<StatusDTO>> getAll() {
        Optional<List<Status>> statusListOptional = statusRepository.findAllStatus();
        if (statusListOptional.isPresent()) {
            return findStatusByAnyInfo(statusListOptional.get());
        } else {
            return BaseResponse.<List<StatusDTO>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: StatusServiceImpl + func: getAll() + return success")
                    .build();
        }
    }

    @Override
    public BaseResponse<List<StatusDTO>> getById(Long statusId) {
        Optional<List<Status>> statusListOptional = statusRepository.findStatusById(statusId);
        if (statusListOptional.isPresent()) {
            return findStatusByAnyInfo(statusListOptional.get());
        } else {
            return BaseResponse.<List<StatusDTO>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: StatusServiceImpl + func: getById(Long statusId) + return success")
                    .build();
        }
    }

    @Override
    public BaseResponse<List<StatusDTO>> getByStatusName(String statusName) {
        Optional<List<Status>> statusListOptional = statusRepository.findStatusByName(statusName);
        if (statusListOptional.isPresent()) {
            return findStatusByAnyInfo(statusListOptional.get());
        } else {
            return BaseResponse.<List<StatusDTO>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: StatusServiceImpl + func: getByStatusName(String statusName) + return success")
                    .build();
        }
    }

    @Override
    public BaseResponse<StatusDTO> updateStatus(Long statusId, StatusUpdateRequest updatedInformation) {
        return null;
    }

    @Override
    public BaseResponse<Void> deleteStatus(Long userId) {
        return null;
    }

    public BaseResponse<List<StatusDTO>> findStatusByAnyInfo(List<Status> statusList){
        try {
            List<StatusDTO> statusDTOList = statusList.stream()
                    .map(statusEntity -> modelMapper.map(statusEntity, StatusDTO.class))
                    .toList();

            return BaseResponse.<List<StatusDTO>>builder()
                    .status(HttpStatus.OK.value())
                    .message("class: StatusServiceImpl + func: findStatusByAnyInfo(List<Status> statusList) + return 1")
                    .data(statusDTOList)
                    .build();

        } catch (Exception e) {
            return BaseResponse.<List<StatusDTO>>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .message("class: UserServiceImpl + func: findStatusByAnyInfo(List<Status> statusList) + return 2")
                    .build();
        }
    }
}
