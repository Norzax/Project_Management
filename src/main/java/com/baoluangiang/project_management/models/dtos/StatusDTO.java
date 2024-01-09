package com.baoluangiang.project_management.models.dtos;

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
public class StatusDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;

    private String statusName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ProjectDTO> projects;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TaskDTO> tasks;
}
