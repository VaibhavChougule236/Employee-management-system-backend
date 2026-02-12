package com.employeemanagement.service;

import java.util.List;

import com.employeemanagement.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto dto);

    List<DepartmentDto> getAllDepartments();

    void deleteDepartment(Long id);
}