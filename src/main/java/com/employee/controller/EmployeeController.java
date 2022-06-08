package com.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dao.EmployeeDaoImpl;
import com.employee.dto.EmployeeDetails;
import com.employee.service.IEmployeeService;

@RestController
public class EmployeeController {
  
  Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);
  
  @Autowired
  IEmployeeService employeeService;
  
  @GetMapping("/employee/{eid}")
  public ResponseEntity<?> fetchEmployeeDetailsById(@PathVariable("eid") int employeeId) {
	  logger.info("Into EmployeeController:fetchEmployeeDetailsById");
	  try {
		  return new ResponseEntity<EmployeeDetails>(employeeService.fetchEmployeeDetails(employeeId) , HttpStatus.OK);
	  }
	  catch(Exception ex) {
		  logger.error("Exception in EmployeeController:fetchEmployeeDetailsById - " + ex.getMessage());
		  return new ResponseEntity<String>("Exception occurs - " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
		  
	  }
  }
  
  @GetMapping("/employee")
  public ResponseEntity<?> fetchAllEmployee() {
	  logger.info("Into EmployeeController:fetchAllEmployee");
	  try {
		  return new ResponseEntity<List<EmployeeDetails>>(employeeService.fetchEmployeeList() , HttpStatus.OK);
	  }
	  catch(Exception ex) {
		  logger.error("Exception in EmployeeController:fetchAllEmployee - " + ex.getMessage());
		  return new ResponseEntity<String>("Exception occurs - " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
		  
	  }
  }
  
  @PostMapping("/employee")
  public ResponseEntity<?> insertEmployee(@RequestBody EmployeeDetails empDetails){
	  logger.info("Into EmployeeController:insertEmployee");
	  try {
		  employeeService.insertEmployee(empDetails);
		  return new ResponseEntity<String>("Successfully inserted in DB.Employee id - " + empDetails.getId() , HttpStatus.OK);
	  }
	  catch(Exception ex) {
		  logger.error("Exception in EmployeeController:insertEmployee - " + ex.getMessage());
		  return new ResponseEntity<String>("Exception occurs - " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
		  
	  }
  }
  
  @GetMapping("/healthCheck")
  public ResponseEntity<String> checkDbHealth(){
	  if(employeeService.checkDBHealth()) {
		  return new ResponseEntity<String>("H2 database is running fine" , HttpStatus.OK);
	  }
	  else {
		  return new ResponseEntity<String>("Exception while accessing the H2 database" , HttpStatus.INTERNAL_SERVER_ERROR);
	  }
  }
}
