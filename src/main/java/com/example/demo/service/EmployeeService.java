package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long employeeId);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updateEmployee);

    void deleteEmployee(Long employeeId);

    List<EmployeeDTO> getAllEmployeeWithAccount();

    EmployeeDTO getEmployeeWithAccountById(Long employeeId);

    EmployeeDTO addDepartmentForEmployee (Long employeeId, Long departmentId);
}
