package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
