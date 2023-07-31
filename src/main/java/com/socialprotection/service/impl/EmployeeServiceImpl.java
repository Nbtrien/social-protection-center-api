package com.socialprotection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialprotection.entity.Employee;
import com.socialprotection.repository.EmployeeRepository;
import com.socialprotection.repository.JobRepository;
import com.socialprotection.repository.ShiftRepository;
import com.socialprotection.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired 
	private JobRepository jobRepository;
	@Autowired
	private ShiftRepository shiftRepository;
	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	@Override
	public Employee findById(long id) {
		return employeeRepository.findOne(id);
	}
	@Override
	public void deleteEmployeeById(long id) {
		employeeRepository.delete(id);
	}
}
