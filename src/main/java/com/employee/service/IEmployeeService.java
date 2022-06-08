package com.employee.service;

import java.util.List;

import com.employee.dto.EmployeeDetails;

public interface IEmployeeService {

	EmployeeDetails fetchEmployeeDetails(int employeeId) throws Exception;

	List<EmployeeDetails> fetchEmployeeList() throws Exception;

	void insertEmployee(EmployeeDetails empdetails) throws Exception;
	
	boolean checkDBHealth();

}