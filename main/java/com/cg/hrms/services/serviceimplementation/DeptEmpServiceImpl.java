package com.cg.hrms.services.serviceimplementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cg.hrms.entities.DeptEmp;
import com.cg.hrms.exceptions.EmployeeNotFoundException;
import com.cg.hrms.exceptions.InvalidInputException;
import com.cg.hrms.repos.DepartmentRepo;
import com.cg.hrms.repos.DeptEmpRepo;
import com.cg.hrms.repos.EmployeeRepo;
import com.cg.hrms.repos.TitleRepo;
import com.cg.hrms.services.serviceinterface.DeptEmpService;
import com.cg.hrms.utility.DeptEmpCompositeKey;

import jakarta.transaction.Transactional;

@Service
public class DeptEmpServiceImpl implements DeptEmpService {

	@Autowired
	private DeptEmpRepo deptEmpRepo;

	// *********************************************************************************//

	/**
	 * Adds a new department-employee record to the repository.
	 *
	 * @param deptemp The DeptEmp entity to be added.
	 * @throws InvalidInputException if the DeptEmp object is null.
	 */
	@Override
	public DeptEmp addNewDeptEmp(DeptEmp deptemp) {
		if (deptemp == null) {
			throw new InvalidInputException("DeptEmp cannot be null");
		}
		return deptEmpRepo.save(deptemp);
	}

	/**
	 * Retrieves all department-employee records from the repository.
	 *
	 * @return List of all department-employee records.
	 * @throws EmployeeNotFoundException if no department-employee records are
	 *                                   found.
	 */
	@Override
	public Page<DeptEmp> getAllDeptEmp(Pageable pageable) {
		Page<DeptEmp> deptEmpList = deptEmpRepo.findAll(pageable);
		if (deptEmpList.isEmpty()) {
			throw new EmployeeNotFoundException("No department-employee records found");
		}
		return deptEmpList;
	}

	/**
	 * Retrieves department-employee records by employee number and department
	 * number.
	 *
	 * @param empNo  The employee number.
	 * @param deptNo The department number.
	 * @return List of department-employee records matching the employee and
	 *         department numbers.
	 * @throws InvalidInputException     if employee number or department number is
	 *                                   invalid.
	 * @throws EmployeeNotFoundException if no records are found for the given
	 *                                   criteria.
	 */
	@Override
	public Page<DeptEmp> getAllDeptEmpByEmpNoAndDeptNo(int empNo, String deptNo, Pageable pageable) {
		if (empNo <= 0) {
			throw new InvalidInputException("Employee number must be greater than zero");
		}
		if (deptNo == null || deptNo.trim().isEmpty()) {
			throw new InvalidInputException("Department number cannot be null or empty");
		}

		DeptEmpCompositeKey deptEmpId = new DeptEmpCompositeKey(empNo, deptNo);

		Page<DeptEmp> deptEmpList = deptEmpRepo.findAllBydeptEmpId(deptEmpId, pageable);
		if (deptEmpList.isEmpty()) {
			throw new EmployeeNotFoundException("No department-employee records found for employee number: " + empNo
					+ " and department number: " + deptNo);
		}
		return deptEmpList;
	}

	/**
	 * Retrieves department-employee records by department number and from date.
	 *
	 * @param deptNo   The department number.
	 * @param fromDate The starting date.
	 * @return List of department-employee records matching the department number
	 *         and from date.
	 * @throws InvalidInputException     if department number is null or empty.
	 * @throws EmployeeNotFoundException if no records are found for the given
	 *                                   criteria.
	 */
	@Override
	public Page<DeptEmp> getAllDeptEmpByDeptNoAndFromDate(String deptNo, Date fromDate, Pageable pageable) {
		if (deptNo == null || deptNo.trim().isEmpty()) {
			throw new InvalidInputException("Department number cannot be null or empty");
		}

		Page<DeptEmp> deptEmpList = deptEmpRepo.findByDeptEmpIdDeptNoAndFromDate(deptNo, fromDate, pageable);

		if (deptEmpList.isEmpty()) {
			throw new EmployeeNotFoundException(
					"No department-employee records found for department number: " + deptNo + " and date: " + fromDate);
		}
		return deptEmpList;
	}

	/**
	 * Retrieves department-employee records by employee number and from date.
	 *
	 * @param empNo    The employee number.
	 * @param fromDate The starting date.
	 * @return List of department-employee records matching the employee number and
	 *         from date.
	 * @throws InvalidInputException     if employee number is invalid.
	 * @throws EmployeeNotFoundException if no records are found for the given
	 *                                   criteria.
	 */
	@Override
	public Page<DeptEmp> getAllDeptEmpByEmpNoAndFromDate(int empNo, Date fromDate, Pageable pageable) {
		if (empNo <= 0) {
			throw new InvalidInputException("Employee number must be greater than zero");
		}

		Page<DeptEmp> deptEmpList = deptEmpRepo.findByDeptEmpIdEmpNoAndFromDate(empNo, fromDate, pageable);

		if (deptEmpList.isEmpty()) {
			throw new EmployeeNotFoundException(
					"No department-employee records found for employee number: " + empNo + " and date: " + fromDate);
		}
		return deptEmpList;
	}

	/**
	 * Retrieves department-employee records by employee number, department number,
	 * and from date.
	 *
	 * @param empNo    The employee number.
	 * @param deptNo   The department number.
	 * @param fromDate The starting date.
	 * @return List of department-employee records matching all specified criteria.
	 * @throws InvalidInputException     if employee number or department number is
	 *                                   invalid.
	 * @throws EmployeeNotFoundException if no records are found for the given
	 *                                   criteria.
	 */
	@Override
	public Page<DeptEmp> getAllDeptEmpByEmpNoAndDeptNoAndFromDate(int empNo, String deptNo, Date fromDate,
			Pageable pageable) {
		if (empNo <= 0) {
			throw new InvalidInputException("Employee number must be greater than zero");
		}
		if (deptNo == null || deptNo.trim().isEmpty()) {
			throw new InvalidInputException("Department number cannot be null or empty");
		}

		DeptEmpCompositeKey deptEmpId = new DeptEmpCompositeKey(empNo, deptNo);

		Page<DeptEmp> deptEmpList = deptEmpRepo.findAllBydeptEmpIdAndFromDate(deptEmpId, fromDate, pageable);
		if (deptEmpList.isEmpty()) {
			throw new EmployeeNotFoundException("No department-employee records found for employee number: " + empNo
					+ " and department number: " + deptNo + " and from date " + fromDate);
		}
		return deptEmpList;
	}

	/**
	 * Updates an existing department-employee record.
	 *
	 * @param empno          The employee number.
	 * @param deptno         The department number.
	 * @param updatedDeptEmp The DeptEmp entity with updated details.
	 * @return The updated DeptEmp entity.
	 * @throws EmployeeNotFoundException if no record is found for the given
	 *                                   employee and department numbers.
	 */
	@Override
	public DeptEmp updateDeptEmp(int empno, String deptno, DeptEmp updatedDeptEmp) {
		DeptEmpCompositeKey compositeKey = new DeptEmpCompositeKey(empno, deptno);

		DeptEmp existingDeptEmp = deptEmpRepo.findByDeptEmpId(compositeKey);

		if (existingDeptEmp != null) {
			existingDeptEmp.setFromDate(updatedDeptEmp.getFromDate());
			existingDeptEmp.setToDate(updatedDeptEmp.getToDate());

			return deptEmpRepo.save(existingDeptEmp);
		} else {
			throw new EmployeeNotFoundException("No department-employee record found for employee number: " + empno
					+ " and department number: " + deptno);
		}
	}

	/**
	 * Updates department-employee records by employee number and from date.
	 *
	 * @param empno          The employee number.
	 * @param fromDate       The starting date.
	 * @param updatedDeptEmp The DeptEmp entity with updated details.
	 * @throws EmployeeNotFoundException if no records are found for the given
	 *                                   employee number and from date.
	 */
	@Override
	public void updateDeptEmpByEmpNoAndFromDate(int empno, Date fromDate, DeptEmp updatedDeptEmp) {
		List<DeptEmp> deptEmpList = deptEmpRepo.findAllByEmployee_EmpNoAndFromDate(empno, fromDate);

		if (deptEmpList.isEmpty()) {
			throw new EmployeeNotFoundException("No department-employee records found for employee number: " + empno
					+ " and from date " + fromDate);
		}

		for (DeptEmp deptEmp : deptEmpList) {
			deptEmp.setFromDate(updatedDeptEmp.getFromDate());
			deptEmp.setToDate(updatedDeptEmp.getToDate());
			deptEmpRepo.save(deptEmp);
		}
	}

	/**
	 * Updates department-employee records by department number and from date.
	 *
	 * @param deptno         The department number.
	 * @param fromDate       The starting date.
	 * @param updatedDeptEmp The DeptEmp entity with updated details.
	 * @return List of updated department-employee records.
	 * @throws EmployeeNotFoundException if no records are found for the given
	 *                                   department number and from date.
	 */
	@Override
	public List<DeptEmp> updateDeptEmpByDeptNoAndFromDate(String deptno, Date fromDate, DeptEmp updatedDeptEmp) {
		List<DeptEmp> deptEmpList = deptEmpRepo.findAllByDepartment_DeptNoAndFromDate(deptno, fromDate);

		if (deptEmpList.isEmpty()) {
			throw new EmployeeNotFoundException("No department-employee records found for department number: " + deptno
					+ " and from date " + fromDate);
		}

		for (DeptEmp deptEmp : deptEmpList) {
			deptEmp.setFromDate(updatedDeptEmp.getFromDate());
			deptEmp.setToDate(updatedDeptEmp.getToDate());
			deptEmpRepo.save(deptEmp);
		}

		return deptEmpList;
	}

	/**
	 * Updates department-employee records by employee number, department number,
	 * and from date.
	 *
	 * @param empno          The employee number.
	 * @param deptno         The department number.
	 * @param fromDate       The starting date.
	 * @param updatedDeptEmp The DeptEmp entity with updated details.
	 * @throws EmployeeNotFoundException if no record is found for the given
	 *                                   criteria.
	 */
	@Override
	public void updateDeptEmpByEmpNoDeptNoAndFromDate(int empno, String deptno, Date fromDate, DeptEmp updatedDeptEmp) {
		DeptEmpCompositeKey compositeKey = new DeptEmpCompositeKey(empno, deptno);

		DeptEmp existingDeptEmp = deptEmpRepo.findByDeptEmpId(compositeKey);

		if (existingDeptEmp != null && existingDeptEmp.getFromDate().equals(fromDate)) {
			existingDeptEmp.setFromDate(updatedDeptEmp.getFromDate());
			existingDeptEmp.setToDate(updatedDeptEmp.getToDate());
			deptEmpRepo.save(existingDeptEmp);
		} else {
			throw new EmployeeNotFoundException("No department-employee record found for employee number: " + empno
					+ " and department number: " + deptno + " with from date " + fromDate);
		}
	}

	/**
	 * Deletes a department-employee record by employee number and department
	 * number.
	 *
	 * @param empno  The employee number.
	 * @param deptno The department number.
	 * @throws EmployeeNotFoundException if no record is found for the given
	 *                                   employee and department numbers.
	 */
	@Override
	public void deleteDeptEmpByEmpNoAndDeptNo(int empno, String deptno) {
		DeptEmpCompositeKey compositeKey = new DeptEmpCompositeKey(empno, deptno);
		DeptEmp existingDeptEmp = deptEmpRepo.findByDeptEmpId(compositeKey);

		if (existingDeptEmp != null) {
			deptEmpRepo.delete(existingDeptEmp);
		} else {
			throw new EmployeeNotFoundException("No department-employee record found for employee number: " + empno
					+ " and department number: " + deptno);
		}
	}

	/**
	 * Deletes department-employee records by department number and from date.
	 *
	 * @param deptno   The department number.
	 * @param fromdate The starting date.
	 * @throws InvalidInputException     if the department number is null or empty.
	 * @throws EmployeeNotFoundException if no records are found for the given
	 *                                   criteria.
	 */
	@Override
	@Transactional
	public void deleteDeptEmpByDeptNoAndFromDate(String deptno, Date fromdate) {
		if (deptno == null || deptno.trim().isEmpty()) {
			throw new InvalidInputException("Department number cannot be null or empty");
		}
		List<DeptEmp> deptEmps = deptEmpRepo.findByDeptEmpIdDeptNoAndFromDate(deptno, fromdate);
		if (deptEmps.isEmpty()) {
			throw new EmployeeNotFoundException("No department-employee records found for department number: " + deptno
					+ " and from date: " + fromdate);
		} else {
			deptEmps.forEach(deptEmp -> deptEmpRepo.deleteByDeptEmpId(deptEmp.getDeptEmpId()));
		}
	}

	/**
	 * Deletes department-employee records by employee number and from date.
	 *
	 * @param empno    The employee number.
	 * @param fromdate The starting date.
	 * @throws InvalidInputException     if the employee number is invalid.
	 * @throws EmployeeNotFoundException if no records are found for the given
	 *                                   criteria.
	 */
	@Override
	@Transactional
	public void deleteDeptEmpByEmpNoAndFromDate(int empno, Date fromdate) {
		if (empno <= 0) {
			throw new InvalidInputException("Employee number must be greater than zero");
		}
		List<DeptEmp> deptEmps = deptEmpRepo.findByDeptEmpIdEmpNoAndFromDate(empno, fromdate);
		if (deptEmps.isEmpty()) {
			throw new EmployeeNotFoundException("No department-employee records found for employee number: " + empno
					+ " and from date: " + fromdate);
		} else {
			deptEmps.forEach(deptEmp -> deptEmpRepo.deleteByDeptEmpId(deptEmp.getDeptEmpId()));
		}
	}

	/**
	 * Deletes department-employee records by employee number, department number,
	 * and from date.
	 *
	 * @param empno    The employee number.
	 * @param deptno   The department number.
	 * @param fromdate The starting date.
	 * @throws InvalidInputException     if employee number or department number is
	 *                                   invalid.
	 * @throws EmployeeNotFoundException if no records are found for the given
	 *                                   criteria.
	 */
	@Override
	public void deleteDeptEmpByEmpNoDeptNoAndFromDate(int empno, String deptno, Date fromdate) {
		if (empno <= 0) {
			throw new InvalidInputException("Employee number must be greater than zero");
		}
		if (deptno == null || deptno.trim().isEmpty()) {
			throw new InvalidInputException("Department number cannot be null or empty");
		}
		DeptEmpCompositeKey compositeKey = new DeptEmpCompositeKey(empno, deptno);
		List<DeptEmp> deptEmps = deptEmpRepo.findByDeptEmpIdAndFromDate(compositeKey, fromdate);
		if (deptEmps.isEmpty()) {
			throw new EmployeeNotFoundException("No department-employee records found for employee number: " + empno
					+ ", department number: " + deptno + " and from date: " + fromdate);
		} else {
			deptEmps.forEach(deptEmp -> deptEmpRepo.deleteByDeptEmpId(deptEmp.getDeptEmpId()));
		}
	}

}
