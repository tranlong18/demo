package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentDTO>> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO saveDepartment = departmentService.createDepartment(departmentDTO);
        ApiResponse<DepartmentDTO> response = ApiResponse.<DepartmentDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Department created successfully")
                .data(saveDepartment)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<DepartmentDTO>> getDepartmentById(@PathVariable("id") Long departmentId) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(departmentId);
        ApiResponse<DepartmentDTO> response = ApiResponse.<DepartmentDTO>builder()
                .status(HttpStatus.OK.value())
                .data(departmentDTO)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDepartment(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Department deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<DepartmentDTO>> updateDepartment(@PathVariable("id") Long departmentId,
                                                                       @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(departmentId, departmentDTO);
        ApiResponse<DepartmentDTO> response = ApiResponse.<DepartmentDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Department update successfully")
                .data(updatedDepartment)
                .build();
        return ResponseEntity.ok(response);
    }
}
