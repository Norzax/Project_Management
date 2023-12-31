package com.baoluangiang.project_management.models.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationUpdateResponse {
    private String displayName;
    private String bio;
    private Date birthday;
    private String avatarUrl;
}
