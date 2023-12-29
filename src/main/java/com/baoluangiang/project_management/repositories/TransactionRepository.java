package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
