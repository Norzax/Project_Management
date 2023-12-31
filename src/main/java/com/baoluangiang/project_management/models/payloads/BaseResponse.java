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
public class BaseResponse<T> {
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private String message;
}
