package com.cg.hrms.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hrms.entities.DeptEmp;
import com.cg.hrms.utility.DeptEmpCompositeKey;

@Repository
public interface DeptEmpRepo extends JpaRepository<DeptEmp, DeptEmpCompositeKey> {

	public Page<DeptEmp> findAllBydeptEmpId(DeptEmpCompositeKey deptEmpId, Pageable pageable);

	public List<DeptEmp> findByDeptEmpIdDeptNoAndFromDate(String deptNo, Date fromDate);

	public Page<DeptEmp> findByDeptEmpIdDeptNoAndFromDate(String deptNo, Date fromDate, Pageable pageable);

	public List<DeptEmp> findByDeptEmpIdEmpNoAndFromDate(int empNo, Date fromDate);

	public Page<DeptEmp> findByDeptEmpIdEmpNoAndFromDate(int empNo, Date fromDate, Pageable pageable);

	public Page<DeptEmp> findAllBydeptEmpIdAndFromDate(DeptEmpCompositeKey deptEmpId, Date fromDate, Pageable pageable);

	public DeptEmp findByDeptEmpId(DeptEmpCompositeKey compositeKey);

	public List<DeptEmp> findAllByEmployee_EmpNoAndFromDate(int empno, Date fromDate);

	public List<DeptEmp> findAllByDepartment_DeptNoAndFromDate(String deptno, Date fromDate);

	public Object deleteByDeptEmpId(DeptEmpCompositeKey deptEmpId);

	public List<DeptEmp> findByDeptEmpIdAndFromDate(DeptEmpCompositeKey compositeKey, Date fromdate);

}
