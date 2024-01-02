package com.baoluangiang.project_management.models.payloads;

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
public class UserUpdateResponse {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String username;

    @JsonIgnore
    private String oldPassword;

    @JsonIgnore
    private String newPassword;

    @JsonIgnore
    private String confirmNewPassword;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private InformationUpdateResponse information;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PhoneUpdateResponse> phones;
}
