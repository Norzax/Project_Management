package com.baoluangiang.project_management.configurations.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenGenerated {
    private String accessToken;
    private Date expiredIn;
}

