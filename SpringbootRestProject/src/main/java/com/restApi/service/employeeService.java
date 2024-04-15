package com.restApi.service;

import java.util.List;

import com.restApi.entity.Employee;

public interface employeeService {
	public List<Employee> getEmployees();
	public Employee getEmpById(int id);
	public Employee savEmployee(Employee employee);
	public Employee updatEmployee(Employee employee, int id);
	public boolean deleteEmployee(int empId);
	

}
