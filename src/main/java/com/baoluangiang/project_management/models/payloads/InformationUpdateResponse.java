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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformationUpdateResponse {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String displayName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String bio;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date birthday;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String avatarUrl;
}
