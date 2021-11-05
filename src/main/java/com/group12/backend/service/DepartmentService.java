package com.group12.backend.service;

import com.group12.backend.model.Department;
import com.group12.backend.model.TempDepartment;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<TempDepartment> getAllDepartments();

    Department getDepartmentById(long id);
}
