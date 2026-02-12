package com.employeemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeemanagement.dto.DepartmentDto;
import com.employeemanagement.entity.Department;
import com.employeemanagement.exception.BusinessException;
import com.employeemanagement.exception.ResourceNotFoundException;
import com.employeemanagement.repository.DepartmentRepository;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.service.DepartmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto dto) {

        if (departmentRepository.existsByName(dto.getName())) {
            throw new BusinessException("Department already exists");
        }

        Department department = new Department();
        department.setName(dto.getName());

        Department saved = departmentRepository.save(department);

        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(d -> {
                    DepartmentDto dto = new DepartmentDto();
                    dto.setId(d.getId());
                    dto.setName(d.getName());
                    return dto;
                })
                .toList();
    }

    @Override
    public void deleteDepartment(Long id) {

        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department not found");
        }

        if (employeeRepository.existsByDepartment_Id(id)) {
            throw new BusinessException("Cannot delete department with assigned employees");
        }

        departmentRepository.deleteById(id);
    }

	@Override
	public DepartmentDto getDepartmentById(Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found"));

		DepartmentDto dto = new DepartmentDto();
		dto.setId(department.getId());
		dto.setName(department.getName());

		return dto;
	}
}
