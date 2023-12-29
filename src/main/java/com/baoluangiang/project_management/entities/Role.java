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
@Table(name = "role")
@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private RoleGroup roleGroup;

    @OneToMany(mappedBy = "role")
    private Set<ProjectRoleUser> projectRoleUsers = new HashSet<>();
}
