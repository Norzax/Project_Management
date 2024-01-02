package com.baoluangiang.project_management.services.phone;

import com.baoluangiang.project_management.entities.Phone;
import com.baoluangiang.project_management.models.dtos.PhoneDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.PhoneUpdateRequest;
import com.baoluangiang.project_management.models.payloads.PhoneUpdateResponse;
import com.baoluangiang.project_management.repositories.PhoneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService{
    private final PhoneRepository phoneRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, ModelMapper modelMapper) {
        this.phoneRepository = phoneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BaseResponse<List<PhoneDTO>> getAll() {
        return null;
    }

    @Override
    public BaseResponse<List<PhoneDTO>> getById(Long userId) {
        return null;
    }

    @Override
    public BaseResponse<List<PhoneUpdateResponse>> updatePhone(Long userId, List<PhoneUpdateRequest> updatedPhones) {
        if (updatedPhones != null) {
            for (PhoneUpdateRequest phone : updatedPhones) {
                Optional<Phone> findPhone = phoneRepository.findPhoneByPhoneNumberAndUserId(phone.getOldPhoneNumber(), userId);
                if(findPhone.isPresent()){
                    boolean checkNewPhoneExistence = phoneRepository.existsPhoneByPhoneNumber(phone.getNewPhoneNumber());
                    if(checkNewPhoneExistence){
                        return BaseResponse.<List<PhoneUpdateResponse>>builder()
                                .status(HttpStatus.BAD_REQUEST.value())
                                .message("class: PhoneServiceImpl + func: updatePhone(Long userId, List<PhoneUpdateRequest> updatedPhones) + return 1")
                                .build();
                    }

                    Phone updatePhone = findPhone.get();
                    updatePhone.setPhoneNumber(phone.getNewPhoneNumber());
                    phoneRepository.save(updatePhone);
                }
            }
        }
        Optional<List<Phone>> userPhoneList = phoneRepository.findPhoneByUserId(userId);
        List<PhoneUpdateResponse> updatedResponses = userPhoneList.stream()
                .map(phoneEntity -> modelMapper.map(phoneEntity, PhoneUpdateResponse.class)).toList();

        return BaseResponse.<List<PhoneUpdateResponse>>builder()
                .message("class: PhoneServiceImpl + func: updatePhone(Long userId, List<PhoneUpdateRequest> updatedPhones) + return 2")
                .data(updatedResponses)
                .status(HttpStatus.OK.value())
                .build();
    }

}
