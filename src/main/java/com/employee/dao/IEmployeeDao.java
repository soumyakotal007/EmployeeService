package com.employee.dao;

import java.util.List;

import com.employee.dto.EmployeeDto;

public interface IEmployeeDao {

	/*Getting all Employees from table*/
	List<EmployeeDto> getAllEmployee() throws Exception;

	/*Getting a specific employee by employee id from table*/
	EmployeeDto getEmployeeById(int itemId) throws Exception;

	/*Adding an employee into database table*/
	int addEmployee(EmployeeDto dto) throws Exception;

	/*delete an employee from database*/
	int deleteEmployee(int id) throws Exception;
	
	boolean checkDBHealth();

}