package com.baoluangiang.project_management.services.currency;

import com.baoluangiang.project_management.models.dtos.CurrencyDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.CurrencyRequest;
import com.baoluangiang.project_management.models.payloads.CurrencyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyService {
    BaseResponse<List<CurrencyResponse>> getAll();
    BaseResponse<List<CurrencyResponse>> getById(Long currencyId);
    BaseResponse<List<CurrencyResponse>> getByCode(String code);
    BaseResponse<CurrencyResponse> create(CurrencyRequest newCurrency);
    BaseResponse<CurrencyResponse> update(Long currencyId, CurrencyRequest currencyUpdateRequest);
    BaseResponse<List<CurrencyDTO>> delete(Long currencyId);
}
