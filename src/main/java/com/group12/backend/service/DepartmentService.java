package com.group12.backend.service;

import com.group12.backend.model.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(long id);
}
