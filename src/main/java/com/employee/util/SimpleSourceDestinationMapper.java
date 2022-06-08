package com.employee.util;

import org.mapstruct.Mapper;

import com.employee.dto.EmployeeDetails;
import com.employee.dto.EmployeeDto;

@Mapper
public interface SimpleSourceDestinationMapper {
   EmployeeDetails getEmployeeDetailsObjectFromDao(EmployeeDto empDto);
   EmployeeDto getEmployeeDaoObjectFromEmployeeDteails(EmployeeDetails employeeDetails);
}
