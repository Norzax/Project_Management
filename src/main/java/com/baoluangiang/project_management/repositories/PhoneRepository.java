package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
