package com.baoluangiang.project_management.services.currency;

import com.baoluangiang.project_management.models.dtos.CurrencyDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.CurrencyRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyService {
    BaseResponse<List<CurrencyDTO>> getAll();
    BaseResponse<List<CurrencyDTO>> getById(Long currencyId);
    BaseResponse<List<CurrencyDTO>> getByCode(String code);
    BaseResponse<CurrencyDTO> create(CurrencyRequest newCurrency);
    BaseResponse<CurrencyDTO> update(Long currencyId, CurrencyRequest currencyUpdateRequest);
    BaseResponse<List<CurrencyDTO>> delete(Long currencyId);
}
