package com.employee.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import com.employee.dto.EmployeeDto;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {
	
	Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);
	
	@Autowired
    JdbcTemplate template;
	
	 /*Getting all Employees from table*/
    @Override
	public List<EmployeeDto> getAllEmployee() throws Exception {
    	try {
	    	logger.info("Into EmployeeDaoImpl::getAllEmployee");
	        List<EmployeeDto> employeeList = template.query("select id, name,address,salary from employee",(result,rowNum)->new EmployeeDto(result.getInt("id"),
	                result.getString("name"),result.getString("address"),result.getDouble("salary")));
	        logger.info("Printing list of employees : " + employeeList);
	        return employeeList;
    	}
    	catch(Exception ex) {
    		logger.error("Exception in EmployeeDaoImpl::getAllEmployee - " + ex.getMessage());
    		throw ex;
    	}
    }
    
    /*Getting a specific employee by employee id from table*/
    @Override
	public EmployeeDto getEmployeeById(int employeeId) throws Exception{
    	try {
	    	logger.info("Into EmployeeDaoImpl:getEmployeeById");
	        String query = "SELECT * FROM employee where id=?";
	        EmployeeDto employeeDtls = template.queryForObject(query,(result,rowNum)->new EmployeeDto(result.getInt("id"),
	                result.getString("name"),result.getString("address"),result.getDouble("salary")) , employeeId);
	        logger.info("Printing employee details : " + employeeDtls);
	        return employeeDtls;
    	}
    	catch(Exception ex) {
    		logger.error("Exception in EmployeeDaoImpl::getEmployeeById - " + ex.getMessage());
    		throw ex;
    	}
    }
    
    /*Adding an employee into database table*/
    @Override
	public int addEmployee(EmployeeDto dto) throws Exception{
    	try {
	    	logger.info("Into EmployeeDaoImpl:addEmployee");
	        String query = "INSERT INTO EMPLOYEE(ID,NAME,ADDRESS,SALARY) VALUES(?,?,?,?)";
	        return template.update(query,dto.getId(),dto.getEmployeeName(),dto.getEmployeeAddress(),dto.getEmployeeSalary());
    	}
    	catch(Exception ex) {
    		logger.error("Exception in EmployeeDaoImpl::addEmployee - " + ex.getMessage());
    		throw ex;
    	}
    }
    
    /*delete an employee from database*/
    @Override
	public int deleteEmployee(int id) throws Exception{
    	try {
	    	logger.info("Into EmployeeDaoImpl:deleteEmployee");
	        String query = "DELETE FROM EMPLOYEE WHERE ID =?";
	        return template.update(query,id);
    	}
    	catch(Exception ex) {
    		logger.error("Exception in EmployeeDaoImpl::addEmployee - " + ex.getMessage());
    		throw ex;
    	}
    }

	@Override
	public boolean checkDBHealth() {
		try {
			if(check()  > 0) {
				return true;
			}
			return false;
		}
		catch(Exception ex) {
			logger.error("Exception in DB healthcheck");
			return false;
		}
		
	}
	
	public int check() {
	     List<Object> results = template.query("select 1 from dual", new 
	           SingleColumnRowMapper<>());
	          return results.size();
	     }
}
