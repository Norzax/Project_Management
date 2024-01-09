package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Currency;
import com.baoluangiang.project_management.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query("select s from status s")
    Optional<List<Status>> findAllStatus();
    @Query("select s from status s where s.id = :statusId")
    Optional<List<Status>> findStatusById(Long statusId);
    @Query("select s from status s where s.statusName = :statusName")
    Optional<List<Status>> findStatusByName(String statusName);
    boolean existsByStatusName(String statusName);
}
