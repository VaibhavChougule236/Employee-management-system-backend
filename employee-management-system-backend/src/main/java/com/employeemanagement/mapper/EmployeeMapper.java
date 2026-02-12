package com.employeemanagement.mapper;

import org.springframework.stereotype.Component;
import com.employeemanagement.dto.EmployeeResponseDto;
import com.employeemanagement.entity.Employee;

@Component
public class EmployeeMapper {

    public EmployeeResponseDto toResponse(Employee employee) {

        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .fullName(employee.getFirstName() + " " + employee.getLastName())
                .email(employee.getEmail())
                .departmentName(
                        employee.getDepartment() != null
                                ? employee.getDepartment().getName()
                                : null
                )
                .managerName(
                        employee.getManager() != null
                                ? employee.getManager().getFirstName() + " " +
                                  employee.getManager().getLastName()
                                : null
                )
                .build();
    }
}
