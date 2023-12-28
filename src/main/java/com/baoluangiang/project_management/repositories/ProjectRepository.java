package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
