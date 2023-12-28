package com.baoluangiang.project_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "status")
@Entity(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_Name")
    private String statusName;

    @Column
    @OneToMany(mappedBy = "status")
    private List<Project> projects;

    @OneToMany(mappedBy = "status")
    private List<Task> tasks;
}
