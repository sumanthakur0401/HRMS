package com.cg.hrms.controller;

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

import com.cg.hrms.dtos.DepartmentDTO;
import com.cg.hrms.entities.Department;
import com.cg.hrms.services.serviceinterface.DepartmentService;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	// Add new department
	@PostMapping("/add")
	public ResponseEntity<String> addDepartment(@RequestBody DepartmentDTO department) {
		departmentService.addDepartment(department.convertToDepartment());
		return ResponseEntity.ok("New department added successfully");
	}

	// Fetch all departments
	@GetMapping("/alldepartment")
	public ResponseEntity<List<DepartmentDTO>> getAllDepartment() {
		List<DepartmentDTO> departments = departmentService.getAllDepartment().stream().map(Department::convertToDTO)
				.toList();
		return ResponseEntity.ok(departments);
	}

	// Search department by deptno
	@GetMapping("/{deptno}")
	public ResponseEntity<DepartmentDTO> findDepartmentByDeptNo(@PathVariable String deptno) {
		Department department = departmentService.findDepartmentByDeptNo(deptno);
		return ResponseEntity.ok(department.convertToDTO());
	}

	// Search department by name
	@GetMapping("/name/{name}")
	public ResponseEntity<DepartmentDTO> findDepartmentByName(@PathVariable String name) {
		Department department = departmentService.findDepartmentByName(name);
		return ResponseEntity.ok(department.convertToDTO());
	}

	// Update department by department deptno
	@PutMapping("/{deptno}")
	public ResponseEntity<String> updateDepartmentByDeptNo(@PathVariable String deptno,
			@RequestBody DepartmentDTO departmentDto) {
		Department d = departmentDto.convertToDepartment();
		departmentService.updateDepartmentByDeptNo(deptno, d);
		return ResponseEntity.ok("Department updated successfully.");
	}

	// Update department by name
	@PutMapping("/name/{name}")
	public ResponseEntity<String> updateDepartmentByName(@PathVariable String name,
			@RequestBody DepartmentDTO departmentDto) {
		Department d = departmentDto.convertToDepartment();
		departmentService.updateDepartmentByName(name, d);
		return ResponseEntity.ok("Department updated successfully.");
	}

	// Delete department by deptno
	@DeleteMapping("/{deptno}")
	public ResponseEntity<String> deleteDepartmentByDeptNo(@PathVariable String deptno) {
		departmentService.deleteDepartmentByDeptNo(deptno);
		return ResponseEntity.ok("Department Deleted Successfully");
	}

	// Delete department by name
	@DeleteMapping("/name/{name}")
	public ResponseEntity<String> deleteDepartmentByName(@PathVariable String name) {
		departmentService.deleteDepartmentByName(name);
		return ResponseEntity.ok("Department with given name deleted Successfully");
	}

}
