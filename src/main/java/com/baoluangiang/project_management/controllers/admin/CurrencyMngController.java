package com.baoluangiang.project_management.controllers.admin;

import com.baoluangiang.project_management.controllers.utils.ResponseStatus;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.CurrencyRequest;
import com.baoluangiang.project_management.models.payloads.CurrencyResponse;
import com.baoluangiang.project_management.services.currency.CurrencyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/admin/currencyManagement")
@AllArgsConstructor
public class CurrencyMngController {
    private final CurrencyService currencyService;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<CurrencyResponse>>> findAll() {
        BaseResponse<List<CurrencyResponse>> response = currencyService.getAll();
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BaseResponse<List<CurrencyResponse>>> findById(@PathVariable("id") Long currencyId) {
        BaseResponse<List<CurrencyResponse>> response = currencyService.getById(currencyId);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<BaseResponse<List<CurrencyResponse>>> findByCode(@PathVariable("code") String currencyCode) {
        BaseResponse<List<CurrencyResponse>> response = currencyService.getByCode(currencyCode);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PutMapping("/create")
    public ResponseEntity<BaseResponse<CurrencyResponse>> create(@RequestBody CurrencyRequest newCurrency) {
        BaseResponse<CurrencyResponse> response = currencyService.create(newCurrency);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<CurrencyResponse>> update(@PathVariable("id") Long currencyId, @RequestBody CurrencyRequest updateCurrency) {
        BaseResponse<CurrencyResponse> response = currencyService.update(currencyId, updateCurrency);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }
}
