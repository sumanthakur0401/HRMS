package com.cg.hrms.services.serviceinterface;

import java.util.Date;
import java.util.List;

import com.cg.hrms.entities.Salary;

public interface SalaryService {

	// Salary Services
	Salary addSalary(Salary salary);

	List<Salary> getAllSalaries();

	Salary findSalaryByEmpNoAndFromDate(int empNo, Date fromDate);

	List<Salary> findAllSalariesByFromDate(Date fromDate);

	List<Salary> findAllSalariesByEmpNo(int empNo);

	List<Salary> findAllSalariesBySalaryRange(int minSalary, int maxSalary);

	List<Salary> updateSalariesByFromDate(Date fromDate, Salary salary);

	List<Salary> updateSalariesByEmpNo(int empNo, Salary salary);

	Salary updateSalaryByEmpNoAndFromDate(int empNo, Date fromDate, Salary salary);

	String deleteSalaryByEmpNoAndFromDate(int empNo, Date fromDate);

	String deleteSalariesByEmpNo(int empNo);

	String deleteSalariesByFromDate(Date fromDate);
	
	List<Salary> findSalariesGreaterThan(double salary);
}
