package com.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employeemanagement.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
    @Query("""
           SELECT e FROM Employee e
           LEFT JOIN FETCH e.department
           LEFT JOIN FETCH e.manager
           """)
    List<Employee> findAllWithDepartmentAndManager();

    @Query("""
           SELECT e FROM Employee e
           LEFT JOIN FETCH e.department
           LEFT JOIN FETCH e.manager
           WHERE e.id = :id
           """)
    Optional<Employee> findByIdWithDepartmentAndManager(Long id);

    boolean existsByDepartment_Id(Long departmentId);

    List<Employee> findByManager_Id(Long managerId);
}
