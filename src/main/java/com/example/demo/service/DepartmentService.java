package com.example.demo.service;

import com.example.demo.dto.DepartmentDTO;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Long departmentId);

    void deleteDepartment(Long departmentId);

    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);
}
