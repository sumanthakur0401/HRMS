package com.cg.hrms.utility;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DeptEmpCompositeKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "emp_no")
	private int empNo;

	@Column(name = "dept_no", length = 4, nullable = false, columnDefinition = "CHAR(4)")
	private String deptNo; 

	public DeptEmpCompositeKey() {
	}

	public DeptEmpCompositeKey(int empNo, String deptNo) {
		this.empNo = empNo;
		this.deptNo = deptNo;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DeptEmpCompositeKey that = (DeptEmpCompositeKey) o;
		return empNo == that.empNo && deptNo.equals(that.deptNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(empNo, deptNo);
	}

	@Override
	public String toString() {
		return "DeptEmpCompositeKey [empNo=" + empNo + ", deptNo=" + deptNo + "]";
	}
}