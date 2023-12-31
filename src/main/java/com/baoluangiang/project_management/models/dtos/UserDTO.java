package com.baoluangiang.project_management.models.dtos;

import com.baoluangiang.project_management.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private Information information;
    private List<Phone> phones;
}
