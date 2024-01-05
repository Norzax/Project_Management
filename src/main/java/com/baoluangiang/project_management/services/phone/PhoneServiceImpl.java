package com.baoluangiang.project_management.services.phone;

import com.baoluangiang.project_management.entities.Phone;
import com.baoluangiang.project_management.entities.User;
import com.baoluangiang.project_management.models.dtos.PhoneDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.PhoneUpdateRequest;
import com.baoluangiang.project_management.models.payloads.PhoneUpdateResponse;
import com.baoluangiang.project_management.repositories.PhoneRepository;
import com.baoluangiang.project_management.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhoneServiceImpl implements PhoneService{
    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public BaseResponse<List<PhoneDTO>> getAll() {
        return null;
    }

    @Override
    public BaseResponse<List<PhoneDTO>> getById(Long userId) {
        return null;
    }

    @Override
    public BaseResponse<List<PhoneUpdateResponse>> updatePhone(Long userId, List<PhoneUpdateRequest> updatePhones) {
        Optional<List<Phone>> optionalPhones = phoneRepository.findPhoneByUserId(userId);
        if (!optionalPhones.isPresent()) {
            return BaseResponse.<List<PhoneUpdateResponse>>builder()
                    .message("class: PhoneServiceImpl + func: updatePhone(Long userId, List<PhoneUpdateRequest> updatedPhones) + return 1")
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
        }

        if(updatePhones != null) {
            for(PhoneUpdateRequest phoneUpdateRequest : updatePhones) {
                Optional<Phone> oldPhone = phoneRepository.findPhoneByPhoneNumberAndUserId(phoneUpdateRequest.getOldPhoneNumber(), userId);
                // Wrong old phone
                if(!oldPhone.isPresent()){
                    return BaseResponse.<List<PhoneUpdateResponse>>builder()
                            .message("class: PhoneServiceImpl + func: updatePhone(Long userId, List<PhoneUpdateRequest> updatedPhones) + return 2")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .build();
                }

                // Existing phone of this user
                Optional<Phone> existingPhoneOfUser = phoneRepository.findPhoneByPhoneNumberAndUserId(phoneUpdateRequest.getNewPhoneNumber(), userId);
                if (existingPhoneOfUser.isPresent()) {
                    return BaseResponse.<List<PhoneUpdateResponse>>builder()
                            .message("class: PhoneServiceImpl + func: updatePhone(Long userId, List<PhoneUpdateRequest> updatedPhones) + return 3")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .build();
                } else {
                    // Existing phone
                    boolean existingPhone = phoneRepository.existsPhoneByPhoneNumber(phoneUpdateRequest.getNewPhoneNumber());
                    if (existingPhone) {
                        return BaseResponse.<List<PhoneUpdateResponse>>builder()
                                .message("class: PhoneServiceImpl + func: updatePhone(Long userId, List<PhoneUpdateRequest> updatedPhones) + return 4")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build();
                    }
                }

                Optional<User> updater = userRepository.findById(userId);
                updater.ifPresent(user -> {
                    Phone newPhone = Phone.builder()
                            .phoneNumber(phoneUpdateRequest.getNewPhoneNumber())
                            .user(user)
                            .build();

                    oldPhone.get().setPhoneNumber(newPhone.getPhoneNumber());
                    phoneRepository.save(oldPhone.get());
                });
            }
        }

        optionalPhones = phoneRepository.findPhoneByUserId(userId);
        List<PhoneUpdateResponse> updatedPhone = optionalPhones.get().stream()
            .map(phoneEntity -> modelMapper.map(phoneEntity, PhoneUpdateResponse.class)).toList();

            return BaseResponse.<List<PhoneUpdateResponse>>builder()
                .message("class: PhoneServiceImpl + func: updatePhone(Long userId, List<PhoneUpdateRequest> updatedPhones) + return success")
                .data(updatedPhone)
                .status(HttpStatus.OK.value())
                .build();
    }
}
