package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Permission, Long> {
}
