package com.baoluangiang.project_management.controllers.admin;

import com.baoluangiang.project_management.controllers.utils.ResponseStatus;
import com.baoluangiang.project_management.models.dtos.CurrencyDTO;
import com.baoluangiang.project_management.models.dtos.UserDTO;
import com.baoluangiang.project_management.models.payloads.BaseResponse;
import com.baoluangiang.project_management.models.payloads.CurrencyRequest;
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
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<CurrencyDTO>>> findAll() {
        BaseResponse<List<CurrencyDTO>> response = currencyService.getAll();
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BaseResponse<List<CurrencyDTO>>> findById(@PathVariable("id") Long currencyId) {
        BaseResponse<List<CurrencyDTO>> response = currencyService.getById(currencyId);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<BaseResponse<List<CurrencyDTO>>> findByCode(@PathVariable("code") String currencyCode) {
        BaseResponse<List<CurrencyDTO>> response = currencyService.getByCode(currencyCode);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PutMapping("/create")
    public ResponseEntity<BaseResponse<CurrencyDTO>> create(@RequestBody CurrencyRequest newCurrency) {
        BaseResponse<CurrencyDTO> response = currencyService.create(newCurrency);
        HttpStatus httpStatus = ResponseStatus.set(response.getStatus());
        return ResponseEntity.status(httpStatus).body(response);
    }
}
