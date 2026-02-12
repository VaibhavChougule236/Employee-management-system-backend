package com.employeemanagement.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.dto.EmployeeRequestDto;
import com.employeemanagement.dto.EmployeeResponseDto;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.mapper.EmployeeMapper;
import com.employeemanagement.service.EmployeeService;

@RestController
public class EmployeeController  {
	
	@Autowired
	public EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<EmployeeResponseDto> getAllEmployees() {
		List<EmployeeResponseDto> employees = employeeService.getAllEmployees();
		return employees.stream().collect(Collectors.toList());
	}
	@GetMapping("/employees/{id}")
	public EmployeeResponseDto getEmployeeById(Long id) {
		return employeeService.getEmployeeById(id);
	}
//	@GetMapping("/employees/{id}/manager")
//	public EmployeeResponseDto getEmployeeManager(Long id) {
//		EmployeeResponseDto employee = employeeService.getEmployeeById(id);
//		if (employee.getManagerName() == null) {
//			return null; // No manager assigned
//		}
//		return employeeService.getEmployeeById(employee.getManagerName().getId());
//	}
	
	@PostMapping("/employees")
	public EmployeeResponseDto createEmployee(@RequestBody EmployeeRequestDto dto) {
		return employeeService.createEmployee(dto);
	}
	
}