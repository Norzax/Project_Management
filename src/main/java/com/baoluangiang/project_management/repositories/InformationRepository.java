package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Information;
import com.baoluangiang.project_management.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
    @Query("select i from information i left join fetch i.user where i.user.id = :userId")
    Optional<Information> findInformationByUserId(@Param("userId") Long userId);
}
