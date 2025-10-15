package com.example.demo.mapper;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Employee;

import java.util.HashSet;
import java.util.Set;

public class EmployeeMapper {
    public static EmployeeDTO mapToEmployeeDTO(Employee employee) {
        AccountDTO accountDTO = new AccountDTO();
        DepartmentDTO departmentDTO = new DepartmentDTO();
        Set<Long> projectIds = new HashSet<Long>();
        if (employee.getDepartment()!= null){
            departmentDTO.setId(employee.getDepartment().getId());
            departmentDTO.setName(employee.getDepartment().getName());
        }
        if (employee.getAccount() != null) {
            accountDTO.setId(employee.getAccount().getId());
            accountDTO.setEmail(employee.getAccount().getEmail());
            accountDTO.setPassword(employee.getAccount().getPassword());
            accountDTO.setEmployeeId(employee.getId());
        }
        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getGender(),
                employee.getAge(),
                accountDTO,
                departmentDTO,
                projectIds
        );
    }

    public static Employee mapToEmployee(EmployeeDTO employeeDTO) {
        return new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getGender(),
                employeeDTO.getAge(),
                null,
                null,
                null
        );
    }
}
