package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    @Query("select c from currency c left join fetch c.projects")
    Optional<List<Currency>> findAllCurrency();
    @Query("select c from currency c left join fetch c.projects where c.id = :currencyId")
    Optional<List<Currency>> findCurrencyById(Long currencyId);
    @Query("select c from currency c left join fetch c.projects where c.code = :code")
    Optional<List<Currency>> findCurrencyByCode(String code);
    boolean existsByCode(String code);
}
