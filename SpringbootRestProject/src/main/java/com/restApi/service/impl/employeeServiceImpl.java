package com.restApi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restApi.entity.Employee;
import com.restApi.exception.resourceNotFound;
import com.restApi.jpaRepo.jpaRepository;
import com.restApi.service.employeeService;

import jakarta.persistence.Id;

@Service
public class employeeServiceImpl implements employeeService {

	@Autowired // this layer has all methods
	jpaRepository repository;

	// get all employees
	@Override
	public List<Employee> getEmployees() {
		return repository.findAll();
	}

	// get employee by id
	@Override
	public Employee getEmpById(int id) {
		Optional<Employee> findById = repository.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		} else {
			return null;
		}

	}

	// add Employee
	@Override
	public Employee savEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public Employee updatEmployee(Employee employee, int id) {
		// first get employee with corresponding id
		Employee emp = this.repository.findById(id).orElseThrow(() -> new resourceNotFound("Employee", "Id", id));
		// setting values of employees
		emp.setEmpFirstName(employee.getEmpFirstName());
		emp.setEmpLastName(employee.getEmpLastName());
		emp.setEmail(employee.getEmail());

		// saveing updated employee into database

		return this.repository.save(emp);
	}

	@Override
	public boolean deleteEmployee(int empId) {
		// .deleteById(empId) return void that why we first chk it 
		// .existsById(empId) return boolean and we can perform logic
		boolean existsById = this.repository.existsById(empId);
		if (existsById) {
			this.repository.deleteById(empId);
			return true;
		} else {
			return false;
		}
	}

}
