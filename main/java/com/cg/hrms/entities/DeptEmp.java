package com.cg.hrms.entities;

import java.util.Date;

import com.cg.hrms.dtos.DeptEmpDTO;
import com.cg.hrms.utility.DeptEmpCompositeKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "dept_emp")
public class DeptEmp {

	@EmbeddedId
	private DeptEmpCompositeKey deptEmpId;

	@Column(name = "from_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fromDate;

	@Column(name = "to_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date toDate;

	@ManyToOne
	@JoinColumn(name = "emp_no", insertable = false, updatable = false)
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "dept_no", insertable = false, updatable = false)
	private Department department;

	public DeptEmp() {
		super();
	}

	public DeptEmpCompositeKey getDeptEmpId() {
		return deptEmpId;
	}

	public void setDeptEmpId(DeptEmpCompositeKey deptEmpId) {
		this.deptEmpId = deptEmpId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public DeptEmp(DeptEmpCompositeKey deptEmpId, Date fromDate, Date toDate, Employee employee,
			Department department) {
		super();
		this.deptEmpId = deptEmpId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.employee = employee;
		this.department = department;
	}

	// Convert this DeptEmpDTO instance to DeptEmp entity
	public DeptEmpDTO convertToDTO() {
		DeptEmpDTO deptEmpDto = new DeptEmpDTO();
		
		deptEmpDto.setEmpNo(this.deptEmpId.getEmpNo());
		deptEmpDto.setDeptNo(this.deptEmpId.getDeptNo());
		deptEmpDto.setFromDate(this.fromDate);
		deptEmpDto.setToDate(this.toDate);
	
		return deptEmpDto;
	}

}
