package com.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.IEmployeeDao;
import com.employee.dto.EmployeeDetails;
import com.employee.dto.EmployeeDto;
import com.employee.util.SimpleSourceDestinationMapper;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	IEmployeeDao dao;
	
	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	private SimpleSourceDestinationMapper mapper
     = Mappers.getMapper(SimpleSourceDestinationMapper.class);
	
	@Override
	public EmployeeDetails fetchEmployeeDetails(int employeeId) throws Exception {
		logger.info("Into EmployeeServiceImpl:fetchEmployeeDetails");
		EmployeeDto empDto = dao.getEmployeeById(employeeId);
		return mapper.getEmployeeDetailsObjectFromDao(empDto);
	}
	
    @Override
	public List<EmployeeDetails> fetchEmployeeList() throws Exception {
    	logger.info("Into EmployeeServiceImpl:fetchEmployeeList");
		List<EmployeeDto> empList = dao.getAllEmployee();
		List<EmployeeDetails> empDetailsList = empList.stream().map(d -> mapper.getEmployeeDetailsObjectFromDao(d)).collect(Collectors.toList());
		return empDetailsList;
	}
    
    @Override
	public void insertEmployee(EmployeeDetails empdetails) throws Exception {
    	logger.info("Into EmployeeServiceImpl:empdetails");
    	dao.addEmployee(mapper.getEmployeeDaoObjectFromEmployeeDteails(empdetails));
    }

	@Override
	public boolean checkDBHealth() {
		// TODO Auto-generated method stub
		return dao.checkDBHealth();
	}
}
