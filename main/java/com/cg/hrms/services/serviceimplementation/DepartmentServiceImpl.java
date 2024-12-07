package com.cg.hrms.services.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hrms.entities.Department;
import com.cg.hrms.exceptions.DepartmentNotFoundException;
import com.cg.hrms.repos.DepartmentRepo;
import com.cg.hrms.services.serviceinterface.DepartmentService;

import jakarta.transaction.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepo deptRepo;

	@Override
	public Department addDepartment(Department department) {
		Department savedDepartment = deptRepo.save(department);
		return savedDepartment;
	}

	@Override
	public List<Department> getAllDepartment() {
		List<Department> departments = deptRepo.findAll();
		return departments;
	}

	@Override
	public Department findDepartmentByDeptNo(String deptNo) {
		return deptRepo.findById(deptNo).orElse(null);
		
	}

	@Override
	public Department findDepartmentByName(String name) {
		List<Department> departments = deptRepo.findAll();
		for (Department department : departments) {
			if (department.getDeptName().equalsIgnoreCase(name)) {
				return department;
			}
		}
		throw new DepartmentNotFoundException("Department with name " + name + " not found");
	}

	@Override
	@Transactional
	public String updateDepartmentByDeptNo(String deptNo, Department department) {
		if (deptRepo.existsById(deptNo)) {
			Department d = deptRepo.findById(deptNo).orElse(null);
			if (d == null)
				throw new DepartmentNotFoundException("Department with deptNo " + deptNo + " not found");
			d.setDeptName(department.getDeptName());
			d.setDeptNo(deptNo);
			deptRepo.save(d);
			return "Department updated successfully";
		} else
			return null;
	}

	@Override
	@Transactional
	public String updateDepartmentByName(String name, Department department) {
		List<Department> departments = deptRepo.findAll();
		for (Department existingDepartment : departments) {
			if (existingDepartment.getDeptName().equalsIgnoreCase(name)) {
				existingDepartment.setDeptName(department.getDeptName());
				deptRepo.save(existingDepartment);
				return "Department updated successfully";
			}
		}
		throw new DepartmentNotFoundException("Department with name " + name + " not found");
	}

	@Override
	@Transactional
	public String deleteDepartmentByDeptNo(String deptNo) {
		Department d = deptRepo.findByDeptNo(deptNo)
				.orElseThrow(() -> new DepartmentNotFoundException("Department with deptNo " + deptNo + " not found"));
		deptRepo.delete(d);
		return "Department deleted successfully";
	}

	@Override
	public String deleteDepartmentByName(String name) {
		List<Department> departments = deptRepo.findAll();
		for (Department department : departments) {
			if (department.getDeptName().equalsIgnoreCase(name)) {
				deptRepo.deleteById(department.getDeptNo());
				return "Department deleted successfully";
			}
		}
		throw new DepartmentNotFoundException("Department with name " + name + " not found");
	}

}
