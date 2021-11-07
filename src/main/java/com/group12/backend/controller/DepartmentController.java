package com.group12.backend.controller;

import com.group12.backend.model.Department;
import com.group12.backend.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    /**
     * Constructor for the DepartmentController
     * @param departmentService
     * @author Plinio
     */
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Method to save the department and give a response
     * @param department
     * @return ResponseEntity
     * @author Plinio
     */
    @PostMapping()
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.CREATED);
    }

    /**
     * Method to get all the departments
     * @return list of all departments
     * @author Plinio
     */
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    /**
     * Method to get filtered departments
     * @param name
     * @param letters
     * @return list with only the departments that match
     * @author Plinio
     */
    @GetMapping("filter")
    public List<Department> getFilteredDepartments(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "letters", required = false) String letters) {
        return departmentService.getFilteredDepartments(name, letters);
    }

    /**
     * Method to get the department by id
     * @param id
     * @return ResponseEntity
     * @author Plinio
     */
    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") long id) {
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }
}
