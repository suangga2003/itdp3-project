package com.bjb.bankmanagement.forextransaction.repository;

import com.bjb.bankmanagement.forextransaction.entity.UserProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfiles, Long> {

}
