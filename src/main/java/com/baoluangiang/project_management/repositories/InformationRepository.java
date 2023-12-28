package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Long> {
}
