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

	@PostMapping
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto dto) {
		return ResponseEntity.ok(departmentService.createDepartment(dto));
	}

	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
		return ResponseEntity.ok(departmentService.getAllDepartments());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
		return ResponseEntity.noContent().build();
	}
}
