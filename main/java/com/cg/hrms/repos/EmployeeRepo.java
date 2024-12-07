package com.cg.hrms.repos;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hrms.entities.Employee;
import com.cg.hrms.utility.Gender;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	   Page<Employee> findByLastName(String lastName, Pageable pageable);

	    Page<Employee> findByFirstName(String firstName, Pageable pageable);

	    Page<Employee> findByGender(Gender gender, Pageable pageable);

	    Page<Employee> findByHireDate(Date hiredate, Pageable pageable);

	    Page<Employee> findByBirthDate(Date birthdate, Pageable pageable);
}
