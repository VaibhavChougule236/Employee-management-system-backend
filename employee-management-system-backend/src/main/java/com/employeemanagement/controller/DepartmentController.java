package com.employeemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employeemanagement.dto.DepartmentDto;
import com.employeemanagement.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;

	//create department
	@PostMapping
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto dto) {
		return ResponseEntity.status(201).body(departmentService.createDepartment(dto));
	}

	//get all department
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
		return ResponseEntity.ok(departmentService.getAllDepartments());
	}
	
	//get department by id
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
		return ResponseEntity.ok(departmentService.getDepartmentById(id));
	}

	//delete department by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
		return ResponseEntity.noContent().build();
	}
}
