package com.baoluangiang.project_management.services.currency;

import com.baoluangiang.project_management.entities.Currency;
import com.baoluangiang.project_management.models.dtos.CurrencyDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.CurrencyRequest;
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
    public BaseResponse<List<CurrencyDTO>> getAll() {
        Optional<List<Currency>> currencyListOptional = currencyRepository.findAllCurrency();
        if (currencyListOptional.isPresent()) {
            return findCurrencyByAnyInfo(currencyListOptional.get());
        } else {
            return BaseResponse.<List<CurrencyDTO>>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("class: CurrencyServiceImpl + func: getAll() + return 1")
                    .build();
        }
    }

    @Override
    public BaseResponse<List<CurrencyDTO>> getById(Long currencyId) {
        return null;
    }

    @Override
    public BaseResponse<List<CurrencyDTO>> getByCode(String code) {
        return null;
    }

    @Override
    public BaseResponse<CurrencyDTO> create(CurrencyRequest newCurrency) {
        boolean isExist = currencyRepository.existsByCode(newCurrency.getCode());
        if(isExist){
            return BaseResponse.<CurrencyDTO>builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("class: CurrencyServiceImpl + func: create(CurrencyRequest newCurrency) + return 1")
                    .build();
        }

        Currency creatingCurrency = Currency.builder()
                .code(newCurrency.getCode())
                .name(newCurrency.getName())
                .build();

        CurrencyDTO createdCurrency = modelMapper.map(currencyRepository.save(creatingCurrency), CurrencyDTO.class);

        return BaseResponse.<CurrencyDTO>builder()
                .status(HttpStatus.OK.value())
                .data(createdCurrency)
                .message("class: CurrencyServiceImpl + func: create(CurrencyRequest newCurrency) + return success")
                .build();
    }

    @Override
    public BaseResponse<CurrencyDTO> update(Long currencyId, CurrencyRequest currencyUpdateRequest) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if(optionalCurrency.isPresent()){
            return BaseResponse.<CurrencyDTO>builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("class: CurrencyServiceImpl + func: create(CurrencyRequest newCurrency) + return 1")
                    .build();
        }

        Currency existingCurrency = optionalCurrency.get();
        existingCurrency.setCode(currencyUpdateRequest.getCode());
        existingCurrency.setName(currencyUpdateRequest.getName());

        CurrencyDTO updatedCurrency = modelMapper.map(currencyRepository.save(existingCurrency), CurrencyDTO.class);
        updatedCurrency.setProjects(null);

        return BaseResponse.<CurrencyDTO>builder()
                .status(HttpStatus.OK.value())
                .data(updatedCurrency)
                .message("class: CurrencyServiceImpl + func: create(CurrencyRequest newCurrency) + return success")
                .build();
    }

    @Override
    public BaseResponse<List<CurrencyDTO>> delete(Long currencyId) {
        return null;
    }

    private BaseResponse<List<CurrencyDTO>> findCurrencyByAnyInfo(List<Currency> currencyList) {
        try {
            List<CurrencyDTO> currencyDTOList = currencyList.stream()
                    .map(currencyEntity -> modelMapper.map(currencyEntity, CurrencyDTO.class))
                    .toList();

            return BaseResponse.<List<CurrencyDTO>>builder()
                    .status(HttpStatus.OK.value())
                    .message("class: CurrencyServiceImpl + func: findCurrencyByAnyInfo(List<CurrencyDTO> currencyList) + return 1")
                    .data(currencyDTOList)
                    .build();

        } catch (Exception e) {
            return BaseResponse.<List<CurrencyDTO>>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .message("class: CurrencyServiceImpl + func: findCurrencyByAnyInfo(List<CurrencyDTO> currencyList) + return 2")
                    .build();
        }
    }
}
