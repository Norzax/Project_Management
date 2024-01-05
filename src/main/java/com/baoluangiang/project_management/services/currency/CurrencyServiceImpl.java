package com.baoluangiang.project_management.services.currency;

import com.baoluangiang.project_management.entities.Currency;
import com.baoluangiang.project_management.models.dtos.CurrencyDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.CurrencyRequest;
import com.baoluangiang.project_management.models.payloads.CurrencyResponse;
import com.baoluangiang.project_management.repositories.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService{
    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;

    @Override
    public BaseResponse<List<CurrencyResponse>> getAll() {
        Optional<List<Currency>> currencyListOptional = currencyRepository.findAllCurrency();
        if (currencyListOptional.isPresent()) {
            return findCurrencyByAnyInfo(currencyListOptional.get());
        } else {
            return BaseResponse.<List<CurrencyResponse>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: CurrencyServiceImpl + func: getAll() + return 1")
                    .build();
        }
    }

    @Override
    public BaseResponse<List<CurrencyResponse>> getById(Long currencyId) {
        Optional<List<Currency>> currencyOptional = currencyRepository.findCurrencyById(currencyId);
        if (currencyOptional.isPresent()) {
            return findCurrencyByAnyInfo(currencyOptional.get());
        } else {
            return BaseResponse.<List<CurrencyResponse>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: CurrencyServiceImpl + func: getById() + return 1")
                    .build();
        }
    }

    @Override
    public BaseResponse<List<CurrencyResponse>> getByCode(String code) {
        Optional<List<Currency>> currencyOptional = currencyRepository.findCurrencyByCode(code);
        if (currencyOptional.isPresent()) {
            return findCurrencyByAnyInfo(currencyOptional.get());
        } else {
            return BaseResponse.<List<CurrencyResponse>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: CurrencyServiceImpl + func: getByCode() + return 1")
                    .build();
        }
    }

    @Override
    public BaseResponse<CurrencyResponse> create(CurrencyRequest newCurrency) {
        boolean isExist = currencyRepository.existsByCode(newCurrency.getCode());
        if(isExist){
            return BaseResponse.<CurrencyResponse>builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("class: CurrencyServiceImpl + func: create(CurrencyRequest newCurrency) + return 1")
                    .build();
        }

        Currency creatingCurrency = Currency.builder()
                .code(newCurrency.getCode())
                .name(newCurrency.getName())
                .build();

        CurrencyResponse createdCurrency = modelMapper.map(currencyRepository.save(creatingCurrency), CurrencyResponse.class);

        return BaseResponse.<CurrencyResponse>builder()
                .status(HttpStatus.OK.value())
                .data(createdCurrency)
                .message("class: CurrencyServiceImpl + func: create(CurrencyRequest newCurrency) + return success")
                .build();
    }

    @Override
    public BaseResponse<CurrencyResponse> update(Long currencyId, CurrencyRequest currencyUpdateRequest) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if(optionalCurrency.isEmpty()){
            return BaseResponse.<CurrencyResponse>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: CurrencyServiceImpl + func: update(Long currencyId, CurrencyRequest currencyUpdateRequest) + return 1")
                    .build();
        }

        Currency existingCurrency = optionalCurrency.get();

        if (currencyUpdateRequest.getCode() != null && currencyId == existingCurrency.getId()) {
            existingCurrency.setCode(currencyUpdateRequest.getCode());
        } else if (currencyId != existingCurrency.getId()) {
            return BaseResponse.<CurrencyResponse>builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("class: CurrencyServiceImpl + func: update(Long currencyId, CurrencyRequest currencyUpdateRequest) + return 2")
                    .build();
        }

        if (currencyUpdateRequest.getName() != null) {
            existingCurrency.setName(currencyUpdateRequest.getName());
        }

        currencyRepository.save(existingCurrency);
        CurrencyResponse updatedCurrency = modelMapper.map(existingCurrency, CurrencyResponse.class);

        return BaseResponse.<CurrencyResponse>builder()
                .status(HttpStatus.OK.value())
                .data(updatedCurrency)
                .message("class: CurrencyServiceImpl + func: update(Long currencyId, CurrencyRequest currencyUpdateRequest) + return success")
                .build();
    }

    @Override
    public BaseResponse<List<CurrencyDTO>> delete(Long currencyId) {
        return null;
    }

    private BaseResponse<List<CurrencyResponse>> findCurrencyByAnyInfo(List<Currency> currencyList) {
        try {
            List<CurrencyResponse> currencyDTOList = currencyList.stream()
                    .map(currencyEntity -> modelMapper.map(currencyEntity, CurrencyResponse.class))
                    .toList();

            return BaseResponse.<List<CurrencyResponse>>builder()
                    .status(HttpStatus.OK.value())
                    .message("class: CurrencyServiceImpl + func: findCurrencyByAnyInfo(List<CurrencyDTO> currencyList) + return 1")
                    .data(currencyDTOList)
                    .build();

        } catch (Exception e) {
            return BaseResponse.<List<CurrencyResponse>>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .message("class: CurrencyServiceImpl + func: findCurrencyByAnyInfo(List<CurrencyDTO> currencyList) + return 2")
                    .build();
        }
    }
}
