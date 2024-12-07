package com.cg.hrms.services.serviceinterface;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cg.hrms.entities.DeptEmp;

public interface DeptEmpService {
	DeptEmp addNewDeptEmp(DeptEmp deptEmp);

	Page<DeptEmp> getAllDeptEmpByDeptNoAndFromDate(String deptno, Date fromDate, Pageable pageable);

	Page<DeptEmp> getAllDeptEmpByEmpNoAndFromDate(int empno, Date fromDate, Pageable pageable);

	Page<DeptEmp> getAllDeptEmpByEmpNoAndDeptNoAndFromDate(int empno, String deptno, Date fromDate, Pageable pageable);

	DeptEmp updateDeptEmp(int empno, String deptno, DeptEmp updatedDeptEmp);

	void updateDeptEmpByEmpNoAndFromDate(int empno, Date fromDate, DeptEmp updatedDeptEmp);

	List<DeptEmp> updateDeptEmpByDeptNoAndFromDate(String deptno, Date fromDate, DeptEmp updatedDeptEmp);

	void updateDeptEmpByEmpNoDeptNoAndFromDate(int empno, String deptno, Date fromDate, DeptEmp updatedDeptEmp);

	void deleteDeptEmpByEmpNoAndDeptNo(int empno, String deptno);

	void deleteDeptEmpByDeptNoAndFromDate(String deptno, Date fromDate);

	void deleteDeptEmpByEmpNoAndFromDate(int empno, Date fromDate);

	void deleteDeptEmpByEmpNoDeptNoAndFromDate(int empno, String deptno, Date fromDate);

	Page<DeptEmp> getAllDeptEmpByEmpNoAndDeptNo(int empNo, String deptNo, Pageable pageable);

	Page<DeptEmp> getAllDeptEmp(Pageable pageable);

}
