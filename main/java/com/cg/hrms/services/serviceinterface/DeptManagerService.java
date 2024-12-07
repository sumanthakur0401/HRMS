package com.cg.hrms.services.serviceinterface;

import java.util.Date;
import java.util.List;

import com.cg.hrms.entities.DeptManager;

public interface DeptManagerService {

	public DeptManager addDeptManager(DeptManager deptManager);

	public List<DeptManager> getAllDeptManagers();

	public DeptManager findDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo);

	public List<DeptManager> findAllDeptManagersByDeptNoAndFromDate(String deptNo, Date fromDate);

	public List<DeptManager> findAllDeptManagersByEmpNoAndFromDate(int empNo, Date fromDate);

	public List<DeptManager> findAllDeptManagersByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate);

	public DeptManager updateDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo, DeptManager deptManager);

	public DeptManager updateDeptManagerByEmpNoAndFromDate(int empNo, Date fromDate, DeptManager deptManager);

	public List<DeptManager> updateDeptManagerByDeptNoAndFromDate(String deptNo, Date fromDate,
			DeptManager deptManager);

	public DeptManager updateDeptManagerByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate,
			DeptManager deptManager);

	public String deleteDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo);

	public DeptManager deleteDeptManagerByEmpNoAndFromDate(int empNo, Date fromDate);

	public List<DeptManager> deleteDeptManagerByDeptNoAndFromDate(String deptNo, Date fromDate);

	public DeptManager deleteDeptManagerByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate);

	DeptManager deleteDeptManager(int empNo, String deptNo, Date fromDate);

}
