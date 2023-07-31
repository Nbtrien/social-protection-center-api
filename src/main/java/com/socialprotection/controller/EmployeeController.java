package com.socialprotection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialprotection.entity.Employee;
import com.socialprotection.service.EmployeeService;

@CrossOrigin
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(value = "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getAll(@PathVariable("id") long id) {
		return ResponseEntity.ok(employeeService.findById(id));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getAll() {
		return ResponseEntity.ok(employeeService.findAll());
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.save(employee));
	}
	
	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("id") long id) {
	    try {
	      employeeService.deleteEmployeeById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
