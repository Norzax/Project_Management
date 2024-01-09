package com.baoluangiang.project_management.services.status;

import com.baoluangiang.project_management.entities.Currency;
import com.baoluangiang.project_management.entities.Status;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.CurrencyResponse;
import com.baoluangiang.project_management.models.payloads.StatusRequest;
import com.baoluangiang.project_management.models.payloads.StatusResponse;
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
    public BaseResponse<List<StatusResponse>> getAll() {
        Optional<List<Status>> statusListOptional = statusRepository.findAllStatus();
        if (statusListOptional.isPresent()) {
            return findStatusByAnyInfo(statusListOptional.get());
        } else {
            return BaseResponse.<List<StatusResponse>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: StatusServiceImpl + func: getAll() + return success")
                    .build();
        }
    }

    @Override
    public BaseResponse<List<StatusResponse>> getById(Long statusId) {
        Optional<List<Status>> statusListOptional = statusRepository.findStatusById(statusId);
        if (statusListOptional.isPresent()) {
            return findStatusByAnyInfo(statusListOptional.get());
        } else {
            return BaseResponse.<List<StatusResponse>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: StatusServiceImpl + func: getById(Long statusId) + return success")
                    .build();
        }
    }

    @Override
    public BaseResponse<List<StatusResponse>> getByStatusName(String statusName) {
        Optional<List<Status>> statusListOptional = statusRepository.findStatusByName(statusName);
        if (statusListOptional.isPresent()) {
            return findStatusByAnyInfo(statusListOptional.get());
        } else {
            return BaseResponse.<List<StatusResponse>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: StatusServiceImpl + func: getByStatusName(String statusName) + return success")
                    .build();
        }
    }

    @Override
    public BaseResponse<StatusResponse> create(StatusRequest newStatus) {
        boolean isExist = statusRepository.existsByStatusName(newStatus.getStatusName());
        if(isExist){
            return BaseResponse.<StatusResponse>builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("class: StatusServiceImpl + func: create(StatusRequest newStatus) + return 1")
                    .build();
        }

        Status creatingStatus = Status.builder()
                .statusName(newStatus.getStatusName())
                .build();

        StatusResponse createdStatus = modelMapper.map(statusRepository.save(creatingStatus), StatusResponse.class);

        return BaseResponse.<StatusResponse>builder()
                .status(HttpStatus.OK.value())
                .data(createdStatus)
                .message("class: StatusServiceImpl + func: create(StatusRequest newStatus) + return success")
                .build();
    }

    @Override
    public BaseResponse<StatusResponse> update(Long statusId, StatusRequest statusUpdateRequest) {
        Optional<Status> optionalStatus = statusRepository.findById(statusId);
        if(optionalStatus.isEmpty()){
            return BaseResponse.<StatusResponse>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: StatusServiceImpl + func: update(Long statusId, StatusRequest statusUpdateRequest) + return 1")
                    .build();
        }

        Status existingStatus = optionalStatus.get();

        if (statusUpdateRequest.getStatusName() != null && statusId == existingStatus.getId()) {
            existingStatus.setStatusName(statusUpdateRequest.getStatusName());
        } else if (statusId != existingStatus.getId()) {
            return BaseResponse.<StatusResponse>builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("class: StatusServiceImpl + func: update(Long statusId, StatusRequest statusUpdateRequest) + return 1")
                    .build();
        }

        statusRepository.save(existingStatus);
        StatusResponse updatedStatus = modelMapper.map(existingStatus, StatusResponse.class);

        return BaseResponse.<StatusResponse>builder()
                .status(HttpStatus.OK.value())
                .data(updatedStatus)
                .message("class: StatusServiceImpl + func: update(Long statusId, StatusRequest statusUpdateRequest) + return success")
                .build();
    }

    @Override
    public BaseResponse<Void> deleteStatus(Long userId) {
        return null;
    }

    public BaseResponse<List<StatusResponse>> findStatusByAnyInfo(List<Status> statusList){
        try {
            List<StatusResponse> statusDTOList = statusList.stream()
                    .map(statusEntity -> modelMapper.map(statusEntity, StatusResponse.class))
                    .toList();

            return BaseResponse.<List<StatusResponse>>builder()
                    .status(HttpStatus.OK.value())
                    .message("class: StatusServiceImpl + func: findStatusByAnyInfo(List<Status> statusList) + return 1")
                    .data(statusDTOList)
                    .build();

        } catch (Exception e) {
            return BaseResponse.<List<StatusResponse>>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .message("class: UserServiceImpl + func: findStatusByAnyInfo(List<Status> statusList) + return 2")
                    .build();
        }
    }
}
