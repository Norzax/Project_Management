package com.baoluangiang.project_management.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String username;

    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean isActive;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private InformationDTO information;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PhoneDTO> phones;
}
