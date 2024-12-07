package com.cg.hrms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hrms.entities.DeptManager;
import com.cg.hrms.services.serviceinterface.DeptManagerService;

@RestController
@RequestMapping("/api/v1/deptmanager")
public class DeptManagerController {

	@Autowired
	private DeptManagerService deptManagerService;

	// Add new deptmanager object in DB
	@PostMapping("/")
	public ResponseEntity<String> addDeptManager(@RequestBody DeptManager deptManager) {
		DeptManager newDeptManager = deptManagerService.addDeptManager(deptManager);
		return ResponseEntity.ok("New employee assigned as manager Successfully");
	}

	// Fetch all deptmanager objects
	@GetMapping("/")
	public ResponseEntity<List<DeptManager>> getAllDeptManagers() {
		List<DeptManager> deptManagers = deptManagerService.getAllDeptManagers();
		return ResponseEntity.ok(deptManagers);
	}

	// Search deptmanager by empno and deptno
	@GetMapping("/empno/{empno}/deptno/{deptno}")
	public ResponseEntity<DeptManager> findDeptManagerByEmpNoAndDeptNo(@PathVariable int empno,
			@PathVariable String deptno) {
		DeptManager deptManager = deptManagerService.findDeptManagerByEmpNoAndDeptNo(empno, deptno);
		return ResponseEntity.ok(deptManager);
	}

	// Fetch all deptmanager objects by deptno and from date
	@GetMapping("/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<List<DeptManager>> findAllDeptManagersByDeptNoAndFromDate(@PathVariable String deptno,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromdate) {
		List<DeptManager> deptManagers = deptManagerService.findAllDeptManagersByDeptNoAndFromDate(deptno, fromdate);
		return ResponseEntity.ok(deptManagers);
	}

	// Fetch all deptmanager objects by empno and from date
	@GetMapping("/empno/{empno}/fromdate/{fromdate}")
	public ResponseEntity<List<DeptManager>> findAllDeptManagersByEmpNoAndFromDate(@PathVariable int empno,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromdate) {
		List<DeptManager> deptManagers = deptManagerService.findAllDeptManagersByEmpNoAndFromDate(empno, fromdate);
		return ResponseEntity.ok(deptManagers);
	}

	// Fetch all deptmanager objects by empno, deptno, and from date
	@GetMapping("/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<List<DeptManager>> findAllDeptManagersByEmpNoDeptNoAndFromDate(@PathVariable int empno,
			@PathVariable String deptno, @PathVariable Date fromdate) {
		List<DeptManager> deptManagers = deptManagerService.findAllDeptManagersByEmpNoDeptNoAndFromDate(empno, deptno,
				fromdate);
		return ResponseEntity.ok(deptManagers);
	}

	// Update deptmanager details for the given empno and deptno
	@PutMapping("/{empno}/{deptno}")
	public ResponseEntity<String> updateDeptManagerByEmpNoAndDeptNo(@PathVariable int empno,
			@PathVariable String deptno, @RequestBody DeptManager deptManager) {
		deptManagerService.updateDeptManagerByEmpNoAndDeptNo(empno, deptno, deptManager);
		return ResponseEntity.ok("Employee assigned to department updated Successfully");
	}

	// Update deptmanager details for the given empno and fromdate
	@PutMapping("/empno/{empno}/fromdate/{fromdate}")
	public ResponseEntity<String> updateDeptManagerByEmpNoAndFromDate(@PathVariable int empno,
			@PathVariable Date fromdate, @RequestBody DeptManager deptManager) {
		deptManagerService.updateDeptManagerByEmpNoAndFromDate(empno, fromdate, deptManager);
		return ResponseEntity.ok("Employee details updated Successfully");
	}

	// Update deptmanager details for the given deptno and fromdate
	@PutMapping("/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<List<DeptManager>> updateDeptManagerByDeptNoAndFromDate(@PathVariable String deptno,
			@PathVariable Date fromdate, @RequestBody DeptManager deptManager) {
		List<DeptManager> updatedDeptManagers = deptManagerService.updateDeptManagerByDeptNoAndFromDate(deptno,
				fromdate, deptManager);
		return ResponseEntity.ok(updatedDeptManagers);
	}

	// Update deptmanager by empno, deptno, and fromdate
	@PutMapping("/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<String> updateDeptManagerByEmpNoDeptNoAndFromDate(@PathVariable int empno,
			@PathVariable String deptno, @PathVariable Date fromdate, @RequestBody DeptManager deptManager) {
		deptManagerService.updateDeptManagerByEmpNoDeptNoAndFromDate(empno, deptno, fromdate, deptManager);
		return ResponseEntity.ok("DeptManager updated Successfully");
	}

	// Delete deptmanager by empno and deptno
	@DeleteMapping("/empno/{empno}/deptno/{deptno}")
	public ResponseEntity<String> deleteDeptManagerByEmpNoAndDeptNo(@PathVariable int empno,
			@PathVariable String deptno) {
		deptManagerService.deleteDeptManagerByEmpNoAndDeptNo(empno, deptno);
		return ResponseEntity.ok("DeptManager deleted Successfully");
	}

	// Delete deptmanager by empno and fromdate
	@DeleteMapping("/empno/{empno}/fromdate/{fromdate}")
	public ResponseEntity<DeptManager> deleteDeptManagerByEmpNoAndFromDate(@PathVariable int empno,
			@PathVariable Date fromdate) {
		DeptManager deletedDeptManager = deptManagerService.deleteDeptManagerByEmpNoAndFromDate(empno, fromdate);
		return ResponseEntity.ok(deletedDeptManager);
	}

	// Delete deptmanager by deptno and fromdate
	@DeleteMapping("/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<List<DeptManager>> deleteDeptManagerByDeptNoAndFromDate(@PathVariable String deptno,
			@PathVariable Date fromdate) {
		List<DeptManager> deletedDeptManagers = deptManagerService.deleteDeptManagerByDeptNoAndFromDate(deptno,
				fromdate);
		return ResponseEntity.ok(deletedDeptManagers);
	}

	// Delete deptmanager by empno, deptno, and fromdate
	@DeleteMapping("/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<DeptManager> deleteDeptManagerByEmpNoDeptNoAndFromDate(@PathVariable int empno,
			@PathVariable String deptno, @PathVariable Date fromdate) {
		DeptManager deletedDeptManager = deptManagerService.deleteDeptManagerByEmpNoDeptNoAndFromDate(empno, deptno,
				fromdate);
		return ResponseEntity.ok(deletedDeptManager);
	}
}
