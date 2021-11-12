package com.group12.backend.service;

import com.group12.backend.model.Department;

import java.util.List;

/**
 * The DepartmentService interface is used to group related methods with empty bodies.
 *
 * The interface is used by the DepartmentServiceImpl.
 */

public interface DepartmentService {
    /**
     * Save a department
     * @param department
     * @return
     */
    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    /**
     * Get filtered departments
     * @param name
     * @param letters
     * @return
     */
    List<Department> getFilteredDepartments(String name, String letters);


    /**
     * Get a department by id
     * @param id
     * @return
     */
    Department getDepartmentById(long id);
}
