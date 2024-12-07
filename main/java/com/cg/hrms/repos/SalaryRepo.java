package com.cg.hrms.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hrms.entities.Salary;
import com.cg.hrms.utility.SalaryCompositeKey;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, SalaryCompositeKey> {
	// Find all salaries by employee number (part of composite key)
	List<Salary> findByIdEmpNo(int empNo);

	// Find all salaries by from date (part of composite key)
	List<Salary> findByIdFromDate(Date fromDate);

	// Find all salaries within a salary range
	List<Salary> findBySalaryBetween(int minSalary, int maxSalary);

	// Find all salaries by employee number and from date
	Salary findByIdEmpNoAndIdFromDate(int empNo, Date fromDate);

	List<Salary> findBySalaryGreaterThan(int salary);

	List<Salary> findBySalaryGreaterThan(double salary);

}
