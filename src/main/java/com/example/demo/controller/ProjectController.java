package com.example.demo.controller;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectDTO>> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO savedProject = projectService.createProject(projectDTO);
        ApiResponse<ProjectDTO> response = ApiResponse.<ProjectDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Project created successfully")
                .data(savedProject)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ApiResponse<ProjectDTO>> addEmployeesToProject(@PathVariable Long projectId,
                                                                         @RequestBody Set<Long> employeeIds) {

        ProjectDTO savedProject = projectService.addEmployeesToProject(projectId, employeeIds);
        ApiResponse<ProjectDTO> response = ApiResponse.<ProjectDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Add successfully")
                .data(savedProject)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        ApiResponse<List<ProjectDTO>> response = ApiResponse.<List<ProjectDTO>>builder()
                .status(HttpStatus.OK.value())
                .message(String.valueOf(projects.size()) + " project")
                .data(projects)
                .build();
        return ResponseEntity.ok(response);
    }

}
