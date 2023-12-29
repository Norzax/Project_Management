package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
