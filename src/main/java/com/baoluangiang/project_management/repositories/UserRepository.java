package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from user u where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("select u from user u join information i join phone p where u.username = :username")
    Optional<User> findAllInfoByUsername(@Param("username") String username);

    @Query("select u from user u join information i join phone p")
    Optional<List<User>> findAllInfo();
}
