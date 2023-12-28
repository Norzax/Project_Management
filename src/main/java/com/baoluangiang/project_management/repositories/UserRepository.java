package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
