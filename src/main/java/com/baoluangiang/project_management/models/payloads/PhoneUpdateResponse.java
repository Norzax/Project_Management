package com.baoluangiang.project_management.models.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneUpdateResponse {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String oldPhoneNumber;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String newPhoneNumber;
}
