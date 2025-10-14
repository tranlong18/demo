package com.example.demo.service.impl;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = ProjectMapper.mapToProject(projectDTO);
        Project saveProject = projectRepository.save(project);
        return ProjectMapper.mapToProjectDTO(saveProject);
    }

    @Override
    public ProjectDTO addEmployeesToProject(Long projectId, Set<Long> employeeIds) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        project.getEmployees().addAll(employees);
        for (Employee emp : employees) {
            emp.getProjects().add(project);
        }
        Project addedProject = projectRepository.save(project);
        return ProjectMapper.mapToProjectDTO(addedProject);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(ProjectMapper::mapToProjectDTO).collect(Collectors.toList());
    }
}
