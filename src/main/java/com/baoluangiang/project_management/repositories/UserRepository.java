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
    @Query("select u from user u left join fetch u.information left join fetch u.phones where u.id = :userId")
    Optional<User> findById(@Param("userId") Long userId);

    @Query("select u from user u left join fetch u.information left join fetch u.phones where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("select u from user u join information i join phone p where u.username = :username")
    Optional<User> findAllInfoByUsername(@Param("username") String username);

    @Query("select u from user u left join fetch u.information left join fetch u.phones")
    List<User> findAllUser();
}
