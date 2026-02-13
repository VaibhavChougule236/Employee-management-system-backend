package com.employeemanagement.controller;

import com.employeemanagement.dto.EmployeeRequestDto;
import com.employeemanagement.dto.EmployeeResponseDto;
import com.employeemanagement.service.EmployeeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	
	//Create Employee
	@PostMapping
	public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto requestDto) {

		EmployeeResponseDto response = employeeService.createEmployee(requestDto);

		return ResponseEntity.status(201).body(response);
	}

	//get all employees with department and manager name
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {

		List<EmployeeResponseDto> employees = employeeService.getAllEmployees();

		return ResponseEntity.ok(employees);
	}

	//get employee by id
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {

		EmployeeResponseDto employee = employeeService.getEmployeeById(id);

		return ResponseEntity.ok(employee);
	}

	//update employee data
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id,
			 @RequestBody EmployeeRequestDto requestDto) {

		EmployeeResponseDto updatedEmployee = employeeService.updateEmployee(id, requestDto);

		return ResponseEntity.ok(updatedEmployee);
	}

	//delete employee by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

		employeeService.deleteEmployee(id);

		return ResponseEntity.noContent().build();
	}
}
