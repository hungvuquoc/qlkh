package com.example.qlkh.repository;

import com.example.qlkh.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    @Query("select f from VerificationCode f where f.username = :username and f.codeSMS = :code")
    VerificationCode getVerificationCodeByUsernameAndCodeSMS(@Param("username") String username, @Param("code") String code);

    @Query("select count(f) from VerificationCode f where f.username = :username and f.type = :type and f.expired > current_timestamp")
    Integer countNumberByUsernameAndType(@Param("username") String username, @Param("type") Integer type);

    @Modifying
    void removeByUsername(String username);

    @Modifying
    @Query("delete from VerificationCode where expired < current_timestamp")
    void removeVerificationCodeExpired();
}
