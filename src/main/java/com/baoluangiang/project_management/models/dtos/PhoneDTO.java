package com.baoluangiang.project_management.models.dtos;

import com.baoluangiang.project_management.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    private Long id;
    private String phoneNumber;
    private User user;
}
