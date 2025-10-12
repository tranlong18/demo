package com.example.demo.mapper;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getGender(),
                employee.getAge(),
                null
        );
    }

    public static Employee mapToEmployee(EmployeeDTO employeeDTO) {
        return new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getGender(),
                employeeDTO.getAge(),
                null
        );
    }

    public static EmployeeDTO mapToEmployeeWithAccountDTO(Employee employee) {

        AccountDTO accountDTO = new AccountDTO();
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
                accountDTO
        );
    }
}
