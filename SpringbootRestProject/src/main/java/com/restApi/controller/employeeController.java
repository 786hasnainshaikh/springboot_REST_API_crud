package com.restApi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restApi.entity.Employee;
import com.restApi.exception.resourceNotFound;
import com.restApi.service.employeeService;

@RestController
public class employeeController {
	
	@Autowired
	employeeService service;
    
	
	// get all employees
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees(){
		 List<Employee> list = this.service.getEmployees();
		 if (list.size() <=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		 else {
			 return new ResponseEntity<List<Employee>>(service.getEmployees(), HttpStatus.OK);
		 }
		
		
	}
	
	// get employee by id
	@GetMapping("/employees/{empId}")
	public ResponseEntity<Employee> getEmpByIdEmployee(@PathVariable int empId) {
		Employee empById = this.service.getEmpById(empId);
		if (empById == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return new ResponseEntity<Employee>(service.getEmpById(empId) , HttpStatus.OK);
		}
		
	}
	
	
	// save employees
	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee savEmployee = this.service.savEmployee(employee);
		if (savEmployee == null) {
			return ( ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}else {
			return new ResponseEntity<Employee>(service.savEmployee(savEmployee), HttpStatus.OK);
		}
		
	}
	
	
	// update employee
	@PutMapping("/employees/{empId}")
	public ResponseEntity<Employee> updatEmployee(@PathVariable int empId , @RequestBody Employee employee) {
		
			return new ResponseEntity<Employee>(service.updatEmployee(employee, empId), HttpStatus.OK);
		
	}
	
	
	// delete empoyee
	@DeleteMapping("/employees/{empId}")
	public ResponseEntity<String> deleteEmp(@PathVariable int empId) {
		
		  boolean deleteEmployee = this.service.deleteEmployee(empId);
		  if (deleteEmployee) {
			return new ResponseEntity<String>("Employee Deleted Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Employee not exist", HttpStatus.NOT_FOUND);
		}
		 
		
	
	}
	
	
	
}
