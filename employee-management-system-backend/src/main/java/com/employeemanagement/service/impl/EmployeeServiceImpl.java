package com.employeemanagement.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.employeemanagement.EmployeeManagementSystemBackendApplication;
import com.employeemanagement.dto.EmployeeRequestDto;
import com.employeemanagement.dto.EmployeeResponseDto;
import com.employeemanagement.entity.Department;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.exception.ResourceNotFoundException;
import com.employeemanagement.mapper.EmployeeMapper;
import com.employeemanagement.repository.DepartmentRepository;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.service.EmployeeService;

import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class EmployeeServiceImpl implements EmployeeService {
//
//	@Autowired
//	private final EmployeeManagementSystemBackendApplication employeeManagementSystemBackendApplication;
//	@Autowired
//	private final EmployeeRepository employeeRepository;
//	@Autowired
//	private final DepartmentRepository departmentRepository;
//
//
//	@Override
//	public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {
//
//		Department department = departmentRepository.findById(dto.getDepartmentId())
//				.orElseThrow(() -> new ResourceNotFoundException("Department not found"));
//
//		Employee manager = null;
//		if (dto.getManagerId() != null) {
//			manager = employeeRepository.findById(dto.getManagerId())
//					.orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
//		}
//
//		Employee employee = new Employee();
//		employee.setFirstName(dto.getFirstName());
//		employee.setLastName(dto.getLastName());
//		employee.setEmail(dto.getEmail());
//		employee.setDepartment(department);
//		employee.setManager(manager);
//
//		Employee saved = employeeRepository.save(employee);
//		return mapToResponse(saved);
//	}
//
//	@Override
//	public List<EmployeeResponseDto> getAllEmployees() {
//		return employeeRepository.findAllWithDepartmentAndManager().stream().map(this::mapToResponse).toList();
//	}
//
//	@Override
//	public EmployeeResponseDto getEmployeeById(Long id) {
//		Employee employee = employeeRepository.findByIdWithDepartmentAndManager(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//		return mapToResponse(employee);
//	}
//
//	@Override
//	public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {
//
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//
//		Department department = departmentRepository.findById(dto.getDepartmentId())
//				.orElseThrow(() -> new ResourceNotFoundException("Department not found"));
//
//		Employee manager = null;
//		if (dto.getManagerId() != null) {
//			manager = employeeRepository.findById(dto.getManagerId())
//					.orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
//		}
//
//		employee.setFirstName(dto.getFirstName());
//		employee.setLastName(dto.getLastName());
//		employee.setEmail(dto.getEmail());
//		employee.setDepartment(department);
//		employee.setManager(manager);
//
//		return mapToResponse(employee);
//	}
//
//	@Override
//	public void deleteEmployee(Long id) {
//
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//
//		List<Employee> subordinates = employeeRepository.findByManager_Id(id);
//		for (Employee subordinate : subordinates) {
//			subordinate.setManager(null);
//		}
//
//		employeeRepository.delete(employee);
//	}
//
//	private EmployeeResponseDto mapToResponse(Employee e) {
//
//		return EmployeeResponseDto.builder().id(e.getId()).fullName(e.getFirstName() + " " + e.getLastName())
//				.email(e.getEmail()).departmentName(e.getDepartment().getName())
//				.managerName(e.getManager() != null ? e.getManager().getFirstName() + " " + e.getManager().getLastName()
//						: null)
//				.build();
//	}
//}


@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {

        Department department = getDepartment(dto.getDepartmentId());
        Employee manager = getManager(dto.getManagerId());

        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(department);
        employee.setManager(manager);

        Employee saved = employeeRepository.save(employee);

        return employeeMapper.toResponse(saved);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAllWithDepartmentAndManager()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {

        Employee employee = employeeRepository
                .findByIdWithDepartmentAndManager(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(getDepartment(dto.getDepartmentId()));
        employee.setManager(getManager(dto.getManagerId()));

        return employeeMapper.toResponse(employee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employeeRepository.findByManager_Id(id)
                .forEach(sub -> sub.setManager(null));

        employeeRepository.delete(employee);
    }


    private Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }

    private Employee getManager(Long id) {
        if (id == null) return null;
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
    }
}
