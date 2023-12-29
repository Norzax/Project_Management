package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
