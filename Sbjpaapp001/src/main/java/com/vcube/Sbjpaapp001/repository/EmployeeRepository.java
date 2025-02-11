package com.vcube.Sbjpaapp001.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.Sbjpaapp001.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
