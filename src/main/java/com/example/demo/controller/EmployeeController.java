package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
        ApiResponse<EmployeeDTO> response = ApiResponse.<EmployeeDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Employee created successfully")
                .data(savedEmployee)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeById(@PathVariable("id") Long employeeId,
                                                                    @RequestParam(value = "include", required = false) String include) {
        EmployeeDTO employeeDTO = "account".equalsIgnoreCase(include) ?
                employeeService.getEmployeeWithAccountById(employeeId) :
                employeeService.getEmployeeById(employeeId);
        ApiResponse<EmployeeDTO> response = ApiResponse.<EmployeeDTO>builder()
                .status(HttpStatus.OK.value())
                .data(employeeDTO)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees(@RequestParam(value = "include", required = false) String include) {
        List<EmployeeDTO> employees = "account".equalsIgnoreCase(include) ?
                employeeService.getAllEmployeeWithAccount() :
                employeeService.getAllEmployees();
        ApiResponse<List<EmployeeDTO>> response = ApiResponse.<List<EmployeeDTO>>builder()
                .status(HttpStatus.OK.value())
                .message(String.valueOf(employees.size()) + "employees")
                .data(employees)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(@PathVariable("id") Long employeeId,
                                                                   @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, employeeDTO);
        ApiResponse<EmployeeDTO> response = ApiResponse.<EmployeeDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Employee updated successfully")
                .data(updatedEmployee)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Employee deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> addDepartmentForEmployee(@RequestParam Long employeeId,
                                                                             @RequestParam Long departmentId) {
        EmployeeDTO employeeAddedDepartment = employeeService.addDepartmentForEmployee(employeeId, departmentId);
        ApiResponse<EmployeeDTO> response = ApiResponse.<EmployeeDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Add successfully")
                .data(employeeAddedDepartment)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
