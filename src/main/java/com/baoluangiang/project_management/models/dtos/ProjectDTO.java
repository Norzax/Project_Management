package com.baoluangiang.project_management.models.dtos;

import com.baoluangiang.project_management.entities.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;

    private String projectName;

    private String description;

    private Date startDate;

    private Date endDate;

    private Double budget;

    private Status status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private UserDTO owner;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private CurrencyDTO currency;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Task> tasks;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Transaction> transactions;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<UserProjectPermission> userProjectPermissions;
}
