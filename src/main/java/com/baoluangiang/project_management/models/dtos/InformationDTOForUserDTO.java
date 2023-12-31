package com.baoluangiang.project_management.models.dtos;

import com.baoluangiang.project_management.entities.User;
import jakarta.persistence.*;

import java.util.Date;

public class InformationDTOForUserDTO {
    private String displayName;
    private String bio;
    private Date birthday;
    private String avatarUrl;
}
