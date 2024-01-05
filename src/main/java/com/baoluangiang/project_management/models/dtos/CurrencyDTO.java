package com.baoluangiang.project_management.models.dtos;

import com.baoluangiang.project_management.entities.Project;
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
public class CurrencyDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;

    private String code;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Project> projects;
}
