package com.example.demo.service;

import com.example.demo.dto.ProjectDTO;

import java.util.List;
import java.util.Set;

public interface ProjectService {
    ProjectDTO createProject (ProjectDTO projectDTO);

    ProjectDTO addEmployeesToProject(Long projectId, Set<Long> employeeIds);

    List<ProjectDTO> getAllProjects();

    ProjectDTO getProjectById(Long projectId);

    void deleteProject(Long projectId);
}
