package com.group12.backend.service;

import com.group12.backend.exception.ResourceNotFoundException;
import com.group12.backend.model.Department;
import com.group12.backend.model.NullChecker;
import com.group12.backend.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private NullChecker nullChecker;

    /**
     * Constructor for the DepartmentServiceImpl
     * @param departmentRepository
     */
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
        nullChecker = new NullChecker();
    }

    /**
     * Method to get the department by the department id
     * @param id The department id
     * @return Department object
     * @throws ResourceNotFoundException
     */
    @Override
    public Department getDepartmentById(long id) {
        Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent())
            return department.get();

        throw new ResourceNotFoundException("Department", "id", id);
    }

    /**
     * Checks if department should be filtered
     * @param department
     * @param name
     * @param letters
     * @return boolean
     */
    private boolean isDepartmentValid(Department department, String name, String letters) {
        boolean equalsName = department.getName().equals(name);
        boolean equalsLetters = department.getLetters().equals(letters);

        if (nullChecker.areAllNotNull(name, letters)) {
            return equalsName && equalsLetters;
        } else if (nullChecker.areAllNotNull(name) && nullChecker.areAllNull(letters)) {
            return equalsName;
        } else if (nullChecker.areAllNotNull(letters) && nullChecker.areAllNull(name)) {
            return equalsLetters;
        }

        return false;
    }

    /**
     * Gets all the departments based on name and letters
     * @param name
     * @param letters
     * @return List of departments
     */
    @Override
    public List<Department> getFilteredDepartments(String name, String letters) {
        List<Department> departments = departmentRepository.findAll();
        List<Department> filteredDepartments = new ArrayList<>();

        if (nullChecker.areAllNull(name, letters))
            throw new ResourceNotFoundException("Departments", "filter", "All parameters are null");

        for (Department department : departments) {
            if (isDepartmentValid(department, name, letters))
                filteredDepartments.add(department);
        }

        return filteredDepartments;
    }

    /**
     * Saves the department
     * @param department
     * @return department
     */
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * Gets all the departments
     * @return all departments
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}