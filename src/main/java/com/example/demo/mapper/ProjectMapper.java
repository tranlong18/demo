package com.example.demo.mapper;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectMapper {
    public static ProjectDTO mapToProjectDTO(Project project) {
        Set<Long> employeeIds = project.getEmployees()
                .stream()
                .map(Employee::getId)
                .collect(Collectors.toSet());
        return new ProjectDTO(project.getId(), project.getTitle(), employeeIds);
    }
    public static Project mapToProject(ProjectDTO projectDTO){
        return new Project(projectDTO.getId(),projectDTO.getTitle(), new HashSet<>());
    }
}
