package com.vcube.Sbjpaapp001.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.Sbjpaapp001.model.Employee;
import com.vcube.Sbjpaapp001.repository.EmployeeRepository;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;

	//@GetMapping("/getemp/{eid}")
	//Optional<Employee> getEmployeeById(@PathVariable long eid){
		//return employeeRepository.findById(eid);
	//}
	
	@PutMapping("/updateemp/{eid}")
	ResponseEntity<Employee> updateEmployee(@PathVariable long eid,@RequestBody Employee empReq) throws ResourceNotFoundException{
		
		Employee employee= employeeRepository.findById(eid)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this id: "+ eid));
		employee.setEid(empReq.getEid());
		employee.setFname(empReq.getFname());
		employee.setLname(empReq.getLname());
		employee.setPhone(empReq.getPhone());
		employee.setAge(empReq.getAge());
		employee.setSalary(empReq.getSalary());
		
		Employee updateEmp=employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmp);
	
	}
	
	@GetMapping("/getemp/{eid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long eid)
	throws ResourceNotFoundException {
		
		Employee employee= employeeRepository.findById(eid)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this id: "+ eid));
						return ResponseEntity.ok().body(employee);
		
	}
	
	
	
	
	@GetMapping("/getemployees")
	List<Employee> getAllEmpInfo() {
		return employeeRepository.findAll();
		
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/employees/create")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

}
