package com.baoluangiang.project_management.models.dtos;

import com.baoluangiang.project_management.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
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
public class InformationDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;
    private String displayName;
    private String bio;
    private Date birthday;
    private String avatarUrl;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private User user;
}
