package com.group12.backend.repository;

import com.group12.backend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Department repository is the DAO
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String Name);
}
