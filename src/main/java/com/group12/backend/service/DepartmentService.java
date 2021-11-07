package com.group12.backend.service;

import com.group12.backend.model.Department;

import java.util.List;

/**
 * The DepartmentService interface is used to group related methods with empty bodies.
 *
 * The interface is used by the DepartmentServiceImpl.
 */

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    List<Department> getFilteredDepartments(String name, String letters);

    Department getDepartmentById(long id);
}
