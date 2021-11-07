package com.group12.backend.repository;

import com.group12.backend.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Role repository is the DAO
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
