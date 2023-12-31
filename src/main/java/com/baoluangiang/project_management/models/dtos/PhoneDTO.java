package com.baoluangiang.project_management.models.dtos;

import com.baoluangiang.project_management.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private User user;
}
