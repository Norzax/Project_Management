package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
