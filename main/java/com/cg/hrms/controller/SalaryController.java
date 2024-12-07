package com.cg.hrms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hrms.dtos.SalaryDTO;
import com.cg.hrms.entities.Salary;
import com.cg.hrms.entities.Title;
import com.cg.hrms.repos.SalaryRepo;
import com.cg.hrms.repos.TitleRepo;
import com.cg.hrms.services.serviceinterface.SalaryService;
import com.cg.hrms.services.serviceinterface.TitleService;

@RestController
@RequestMapping("/api/v1/salary")
public class SalaryController {
	@Autowired
	private SalaryService salaryService;

	@Autowired
	private TitleService titleService;

	@Autowired
	private SalaryRepo salaryRepo;

	@Autowired
	private TitleRepo titleRepo;

	@RestController
	@RequestMapping("/api/v1/salaries")
	public class SalariesController {

		@Autowired
		private SalaryService salariesService;

		// Add new salary object in DB
		@PostMapping("/")
		public ResponseEntity<String> addSalary(@RequestBody Salary salary) {
			Salary newSalary = salariesService.addSalary(salary);
			return ResponseEntity.ok("New Salary details added Successfully");
		}

		// Fetch all salary objects
		@GetMapping("/")
		public ResponseEntity<List<Salary>> getAllSalaries() {
			List<Salary> salaries = salariesService.getAllSalaries();
			return ResponseEntity.ok(salaries);
		}

		// Search salaries by from date, empno
		@GetMapping("/empno/{empno}/fromdate/{fromdate}")
		public ResponseEntity<Salary> findSalaryByEmpNoAndFromDate(@PathVariable int empno,
				@PathVariable Date fromdate) {
			Salary salary = salariesService.findSalaryByEmpNoAndFromDate(empno, fromdate);
			return ResponseEntity.ok(salary);
		}

		// Fetch all salary objects by from date
		@GetMapping("/fromdate/{fromdate}")
		public ResponseEntity<List<Salary>> findAllSalariesByFromDate(@PathVariable Date fromdate) {
			List<Salary> salaries = salariesService.findAllSalariesByFromDate(fromdate);
			return ResponseEntity.ok(salaries);
		}

		// Fetch all salary objects by empno
		@GetMapping("/empno/{empno}")
		public ResponseEntity<List<Salary>> findAllSalariesByEmpNo(@PathVariable int empno) {
			List<Salary> salaries = salariesService.findAllSalariesByEmpNo(empno);
			return ResponseEntity.ok(salaries);
		}

		// Fetch all salary objects by salary range
		@GetMapping("/salary/{minsalary}/{maxsalary}")
		public ResponseEntity<List<Salary>> findAllSalariesBySalaryRange(@PathVariable int minsalary,
				@PathVariable int maxsalary) {
			List<Salary> salaries = salariesService.findAllSalariesBySalaryRange(minsalary, maxsalary);
			return ResponseEntity.ok(salaries);
		}

		// Update salary details for the given fromdate
		@PutMapping("/fromdate/{fromdate}")
		public ResponseEntity<List<Salary>> updateSalariesByFromDate(@PathVariable Date fromdate,
				@RequestBody Salary salary) {
			List<Salary> updatedSalaries = salariesService.updateSalariesByFromDate(fromdate, salary);
			return ResponseEntity.ok(updatedSalaries);
		}

		// Update salary details for the given empid
		@PutMapping("/empno/{empno}")
		public ResponseEntity<List<Salary>> updateSalariesByEmpNo(@PathVariable int empno, @RequestBody Salary salary) {
			List<Salary> updatedSalaries = salariesService.updateSalariesByEmpNo(empno, salary);
			return ResponseEntity.ok(updatedSalaries);
		}

		// Update salary by empno, fromdate
		@PutMapping("/empno/{empno}/fromdate/{fromdate}")
		public ResponseEntity<String> updateSalaryByEmpNoAndFromDate(@PathVariable int empno,
				@PathVariable Date fromdate, @RequestBody Salary salary) {
			Salary updatedSalary = salariesService.updateSalaryByEmpNoAndFromDate(empno, fromdate, salary);
			return ResponseEntity.ok("Salary updated Successfully");
		}

		// Delete salary by empno and from date
		@DeleteMapping("/empno/{empno}/fromdate/{fromdate}")
		public ResponseEntity<String> deleteSalaryByEmpNoAndFromDate(@PathVariable int empno,
				@PathVariable Date fromdate) {
			salariesService.deleteSalaryByEmpNoAndFromDate(empno, fromdate);
			return ResponseEntity.ok("Salary deleted Successfully");
		}

		// Delete salary by empno
		@DeleteMapping("/{empno}")
		public ResponseEntity<String> deleteSalaryByEmpNo(@PathVariable int empno) {
			salariesService.deleteSalariesByEmpNo(empno);
			return ResponseEntity.ok("Salary deleted by empno Successfully");
		}

		// Delete salary by fromdater̥
		@DeleteMapping("/fromdate/{fromdate}")
		public ResponseEntity<String> deleteSalaryByFromDate(@PathVariable Date fromdate) {
			salariesService.deleteSalariesByFromDate(fromdate);
			return ResponseEntity.ok("Salary deleted by fromdate Successfully");
		}

	}

}
