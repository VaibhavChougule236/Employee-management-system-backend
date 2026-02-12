package com.employeemanagement.dto;


import lombok.Data;

@Data
public class EmployeeRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;
    private Long managerId;
}
