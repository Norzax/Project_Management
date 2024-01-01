package com.baoluangiang.project_management.models.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneUpdateResponse {
    private String oldPhoneNumber;
    private String newPhoneNumber;
}
