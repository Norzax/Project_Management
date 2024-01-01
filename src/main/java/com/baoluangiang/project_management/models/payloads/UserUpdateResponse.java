package com.baoluangiang.project_management.models.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponse {
    private String username;

    private String oldPassword;

    private String newPassword;

    private String confirmNewPassword;

    private InformationUpdateResponse information;

    private List<PhoneUpdateResponse> phones;
}
