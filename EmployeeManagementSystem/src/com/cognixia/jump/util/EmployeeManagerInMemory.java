package com.cognixia.jump.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cognixia.jump.exceptions.EmployeeNotFoundException;
import com.cognixia.jump.model.Employee;

public class EmployeeManagerInMemory implements EmployeeManager {
	
	private static int idCounter = 1;
	private static List<Employee> employeeList = new ArrayList<Employee>();
	
	static {
		employeeList.add(new Employee(idCounter++, "Tom", "HR", 50000, "tom@email.com"));
		employeeList.add(new Employee(idCounter++, "Mary", "HR", 50000, "mary@email.com"));
		employeeList.add(new Employee(idCounter++, "Anna", "IT", 50000, "anna@email.com"));
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeList;
	}

	@Override
	public Employee findEmployeeById(int id) throws EmployeeNotFoundException {
		
		Optional<Employee> empl = employeeList.stream()
				.filter((e) -> e.getId() == id)
				.findFirst();
		
		if (empl.isEmpty()) {
			throw new EmployeeNotFoundException(id);
		}
		
		Employee emplObj = empl.get();
		return emplObj;
	}
	

	@Override
	public boolean createEmployee(Employee empl) {
		
		// reset id to be unique using the counter
		empl.setId(idCounter++);
		
		employeeList.add(empl);
		
		System.out.println(empl.getName() + " has been added to the Employee List!");
		
		return false;
	}

	@Override
	public boolean deleteEmployee(int id) {
		
		for(Employee e: employeeList) {
		
			if(e.getId() == id) {
			
				employeeList.remove(e);
			}
		}
		
		System.out.println("Employee with an ID of " + id + " has been deleted.");
		
		return false;
	}

	@Override
	public boolean updateEmployee(Employee empl) {
		
		for(Employee e : employeeList) {
		
			if(e.getId() == empl.getId()) {
			
				employeeList.remove(e);
				
				employeeList.add(empl);
				}
			}
		return false;
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String dept) {
		
		for(Employee e: employeeList) {
		
			if(e.getDepartment().equals(dept.toUpperCase())) {
			
				System.out.println(e + "\n");
			}
		}
		return null;
	}

}
