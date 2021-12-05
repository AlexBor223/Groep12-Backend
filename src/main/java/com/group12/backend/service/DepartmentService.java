package com.group12.backend.service;

import com.group12.backend.model.Department;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    List<Department> getFilteredDepartments(String name, String letters);

    Department getDepartmentById(long id);
}
