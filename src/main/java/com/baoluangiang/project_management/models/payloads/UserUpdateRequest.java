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
public class UserUpdateRequest {
    private String username;

    private String email;

    private String oldPassword;

    private String newPassword;

    private String confirmNewPassword;

    private InformationUpdateRequest information;

    private List<PhoneUpdateRequest> phones;
}
