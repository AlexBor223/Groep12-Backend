package com.group12.backend.service;

import com.group12.backend.exception.ResourceNotFoundException;
import com.group12.backend.model.Abbreviation;
import com.group12.backend.model.Department;
import com.group12.backend.model.TempAbbreviation;
import com.group12.backend.model.TempDepartment;
import com.group12.backend.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentById(long id) {
        Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent())
            return department.get();

        throw new ResourceNotFoundException("Department", "id", id);
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<TempDepartment> getAllDepartments() {

        return depListToTDepList(departmentRepository.findAll());
    }


    private  List<TempDepartment> depListToTDepList(List<Department> Departments){
        List<TempDepartment> newList = new ArrayList<TempDepartment>(Departments.size());

        for (Department abr : Departments) {
            newList.add(new TempDepartment(abr));
        }
        return newList;
    }
}