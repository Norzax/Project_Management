package com.baoluangiang.project_management.configurations.security;

import com.baoluangiang.project_management.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenGenerated {
    private String refreshToken;
    private Date expiredIn;
    private User user;
}
