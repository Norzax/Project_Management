package com.baoluangiang.project_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permission")
@Entity(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permission_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "permission")
    private Set<UserProjectPermission> userProjectPermissions = new HashSet<>();

    @OneToMany(mappedBy = "permission")
    private Set<UserTaskPermission> userTaskPermissions = new HashSet<>();
}
