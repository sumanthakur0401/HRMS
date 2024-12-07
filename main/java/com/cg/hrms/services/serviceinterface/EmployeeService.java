package com.cg.hrms.services.serviceinterface;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cg.hrms.entities.Employee;
import com.cg.hrms.utility.Gender;

public interface EmployeeService {
	Employee addNewEmployee(Employee employee);

	Page<Employee> getAllEmployees(Pageable pageable);

	Page<Employee> getAllEmployeeByLastName(String lastName, Pageable pageable);

	Page<Employee> getAllEmployeeByFirstName(String firstName, Pageable pageable);

	Employee getEmployeeById(int empno);

	void updateEmployee(Employee employee);

	Page<Employee> getAllEmployeeByGender(Gender gender, Pageable pageable);

	Page<Employee> getEmployeeByHireDate(Date hiredate, Pageable pageable);

	Page<Employee> getEmployeeByBirthDate(Date birthdate, Pageable pageable);
}
