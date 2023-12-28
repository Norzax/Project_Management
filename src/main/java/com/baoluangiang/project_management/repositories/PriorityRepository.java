package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
