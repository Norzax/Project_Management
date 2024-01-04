package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName (@Param("roleName") String roleName);
}
