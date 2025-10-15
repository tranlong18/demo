package com.example.demo.mapper;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentMapper {
    public static DepartmentDTO mapToDepartmentDTO(Department department) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        if (!department.getEmployees().isEmpty()) {
            employeeDTOList = department.getEmployees()
                    .stream()
                    .map(e -> new EmployeeDTO(e.getId(), e.getFirstName(), e.getLastName(), e.getGender(), e.getAge(), null, null, null)).toList();
        }
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                employeeDTOList);
    }

    public static Department mapToDepartment(DepartmentDTO departmentDTO) {
        return new Department(
                departmentDTO.getId(),
                departmentDTO.getName(),
                null);
    }
}
