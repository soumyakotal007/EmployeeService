package com.employee.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeDetails {
	
	@NotNull
	@Min(3)
	private int id;
	
	@NotBlank(message = "Employee name cannot be blank")
	@Size(min=3, max=50)
	private String employeeName;
	
	@NotBlank(message = "Employee address cannot be blank")
	@Size(min=3, max=50)
	private String employeeAddress;
	
	@NotNull
	@DecimalMin("10000")
	@DecimalMax("99999")
	private double employeeSalary;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

}
