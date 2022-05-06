package com.cognixia.jump.util;

import java.util.List;

import com.cognixia.jump.exceptions.EmployeeNotFoundException;
import com.cognixia.jump.model.Employee;

public class EmployeeManagerFile implements EmployeeManager {

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findEmployeeById(int id) throws EmployeeNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createEmployee(Employee empl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployee(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployee(Employee empl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String dept) {
		// TODO Auto-generated method stub
		return null;
	}

}
