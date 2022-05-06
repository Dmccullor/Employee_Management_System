package com.cognixia.jump;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.cognixia.jump.exceptions.EmployeeNotFoundException;
import com.cognixia.jump.model.Employee;
import com.cognixia.jump.util.EmployeeManager;
import com.cognixia.jump.util.EmployeeManagerFile;
import com.cognixia.jump.util.EmployeeManagerInMemory;

// CRUD Operations - Create Read Update Delete

// Create an EMS that allows us to:
// 1. view all employees
// 2. view particular employee
// 3. create employees
// 4. delete employees
// 5. update employees
// 6. view all employees in a singular department (ex: all employees in HR)
// Expect: Console Based Menu


/*
 * Assignment:
 * - finish the EmployeeManagerInMemory (implement rest of methods)
 * - set up the create employee section of the menu
 * - send it over through slack (files, zip, or github)
 * */

public class Main {

	private static EmployeeManager manager;
	private static Scanner sc;

	public static void main(String[] args) {

		manager = new EmployeeManagerInMemory();
		//manager = new EmployeeManagerFile();
		
		sc = new Scanner(System.in);

		System.out.println("WELCOME TO THE EMPLOYEE MANAGEMENT SYSTEM (EMS)\n");

		mainMenu();

	}

	
	
	public static void mainMenu() {

		while (true) {

			try {
				System.out.println("\nPlease enter one of the following options :" 
									+ "\n1.) View Employees"
									+ "\n2.) Select Employee By Id" 
									+ "\n3.) Create Employee" 
									+ "\n4.) Update Employee"
									+ "\n5.) Delete Employee" 
									+ "\n6.) Exit");

				int option = sc.nextInt();
				sc.nextLine(); // getting rid of new line character

				switch (option) {
				case 1:
					viewEmployees();
					break;
				case 2:
					selectEmployeeById();
					break;
				case 3:
					createNewEmployee();
					break;
				case 4:
					updateEmployee();
					break;
				case 5:
					deleteEmployee();
					break;
				case 6:
					break;

				default:
					System.out.println("\nPlease enter a number between 1 and 6");
					break;
				}

				if (option == 6) {
					break;
				}

			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nPlease enter a number between 1 and 6");
				
			} catch (EmployeeNotFoundException e) {
				System.out.println(e.getMessage());
			}

		}

	}
	
	
	
	public static void viewEmployees() {
		
		
		while(true) {
			
			try {
			
				System.out.println("Select one of the following:" + 
							"\n1. Select all employees" +
						    "\n2. Select employees by department" +
							"\n3. Exit to return to main menu");
				
				int option = sc.nextInt();
				sc.nextLine();
				
				switch (option) {
				case 1:
					viewAllEmployees();
					break;
				case 2:
					inputDepartment();
					break;
				case 3:
					break;
				default:
					System.out.println("Enter number between 1 and 3");
					break;
				}
				
				if(option == 3) {
					break;
				}
				
				
			} catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("Enter number between 1 and 3");
			}
		}
		
	}
	
	
	
	public static void viewAllEmployees() {
		List<Employee> employees = manager.getAllEmployees();
		
		
		if(employees.isEmpty()) {
			System.out.println("No employees currently in EMS");
		}
		else {
			for(Employee e : employees) {
				System.out.println(e);
			}
		}
	}
	
	public static void inputDepartment() {
		try {
			System.out.println("Please enter department.");
			String dept = sc.nextLine().toUpperCase();
			manager.getEmployeesByDepartment(dept);
			
		} catch(InputMismatchException e) {
			System.out.println("Input Invalid. Please try again.");
			inputDepartment();
		}
	}
	
	
	public static void selectEmployeeById() throws EmployeeNotFoundException {
		
		try {
			System.out.println("Please enter the employee's ID.");
			
			int empId = sc.nextInt();
			
			Employee emp = manager.findEmployeeById(empId);
			
			System.out.println(emp);
		
		} catch(InputMismatchException e) {
			
			System.out.println("Invalid Input. Please try again.");
			
			selectEmployeeById();
		}
	}
	
	
	
	public static void createNewEmployee() {
		
		try {
			System.out.println("Please enter their name");
			String name = sc.nextLine();

		
			System.out.println("Please enter their department");
			String department = sc.nextLine();
	
			System.out.println("Please enter their salary");
			int salary = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Please enter their email address");
			String email = sc.nextLine();
		
		
			Employee newEmp = new Employee(0, name, department, salary, email);
			manager.createEmployee(newEmp);
		} catch(InputMismatchException e) {
			System.out.println("Input Invalid. Please start over and try again");
			createNewEmployee();
		}
	}
	
	
	
	public static void deleteEmployee() throws EmployeeNotFoundException {
		int empId;
		
		try {
			System.out.println("Please enter the employee's ID");
			empId = sc.nextInt();
			// implement EmployeeNotFound exception
			manager.deleteEmployee(empId);
			
		} catch(InputMismatchException e) {
			System.out.println("Input invalid. Please start over and try again.");
			deleteEmployee();
		}
		
	}
	
	
	
	public static void updateEmployee() throws EmployeeNotFoundException {

		try {
			System.out.println("Please enter the ID of the employee that you want to update");
			int empId = sc.nextInt();
			sc.nextLine();
			
			Employee emplObj = manager.findEmployeeById(empId);
			int emplId = emplObj.getId();
			String emplName = emplObj.getName();
			String emplDept = emplObj.getDepartment();
			int emplSalary = emplObj.getSalary();
			String emplEmail = emplObj.getEmail();
			
			while(true) {
					
				System.out.println("Please select what you would like to update:" +
									"1. Name" +
									"2. Department" +
									"3. Salary" +
									"4. Email" +
									"5. Return to Main Menu");
				int option = sc.nextInt();
				sc.nextLine();
				
				switch(option) {
				case 1:
					System.out.println("Please enter the updated name.");
					String newName = sc.nextLine();
					Employee newNameEmpl = new Employee(emplId, newName, emplDept, emplSalary, emplEmail);
					manager.updateEmployee(newNameEmpl);
					break;
				case 2:
					System.out.println("Please enter the udpated department.");
					String newDept = sc.nextLine();
					Employee newDeptEmpl = new Employee(emplId, emplName, newDept, emplSalary, emplEmail);
					manager.updateEmployee(newDeptEmpl);
					break;
				case 3:
					System.out.println("Please enter the updated salary.");
					int newSalary = sc.nextInt();
					sc.nextLine();
					Employee newSalaryEmpl = new Employee(emplId, emplName, emplDept, newSalary, emplEmail);
					manager.updateEmployee(newSalaryEmpl);
					break;
				case 4:
					System.out.println("Please enter the updated email address.");
					String newEmail = sc.nextLine();
					Employee newEmailEmpl = new Employee(emplId, emplName, emplDept, emplSalary, newEmail);
					manager.updateEmployee(newEmailEmpl);
					break;
				case 5:
					break;
				default:
					System.out.println("Please enter a number between 1 and 5");
				}
				
				if (option == 5) {
					break;
				}
			} 
		} catch(InputMismatchException e) {
			System.out.println("Invalid Input. Please start over and try again.");
			updateEmployee();
		}
	}
}
