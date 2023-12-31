package com.baoluangiang.project_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "user")
    private Information information;

    @OneToMany(mappedBy = "user")
    private List<Phone> phones;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Attachment> attachments;

    @OneToMany(mappedBy = "user")
    private Set<UserProjectPermission> userProjectPermissions = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserTaskPermission> userTaskPermissions = new HashSet<>();
}
