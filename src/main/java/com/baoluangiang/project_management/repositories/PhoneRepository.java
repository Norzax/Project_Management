package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    @Query("select p from phone p left join fetch p.user where p.user.id = :userId")
    Optional<List<Phone>> findPhoneByUserId(@Param("userId") Long userId);
    @Query("select p from phone p left join fetch p.user where p.phoneNumber = :phoneNumber and p.user.id = :userId")
    Optional<Phone> findPhoneByPhoneNumberAndUserId(@Param("phoneNumber") String phoneNumber, @Param("userId") Long userId);

//    @Query("select case when count (p.id) > 0 then true else false end from phone p where p.phoneNumber = :phoneNumber and p.user.id != : userId")
//    boolean existsPhoneByPhoneNumber(@Param("phoneNumber") String phoneNumber, @Param("userId") Long userId);
    boolean existsPhoneByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
