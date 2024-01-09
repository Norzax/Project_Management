package com.baoluangiang.project_management.models.dtos;

import com.baoluangiang.project_management.entities.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;

    private String title;

    private String description;

    private Date dueDate;

    private Priority priority;

    private Status status;

    private Project project;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Attachment> attachments;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Comment> comments;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Transaction> transactions;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<UserTaskPermission> userTaskPermissions;
}
