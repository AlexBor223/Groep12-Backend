package com.group12.backend.repository;

import com.group12.backend.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repository is the DAO
 */
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
