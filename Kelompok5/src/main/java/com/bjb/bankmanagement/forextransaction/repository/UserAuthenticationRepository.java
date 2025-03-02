package com.bjb.bankmanagement.forextransaction.repository;

import com.bjb.bankmanagement.forextransaction.entity.UserAuthentications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthenticationRepository extends JpaRepository<UserAuthentications, Long> {
    Optional<UserAuthentications> findByEmail(String email);
    UserAuthentications findByUserProfileId(Long userProfileId);
}