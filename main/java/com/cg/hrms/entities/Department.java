package com.cg.hrms.entities;

import java.util.Objects;
import java.util.Set;

import com.cg.hrms.dtos.DepartmentDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

	@Id
	@Column(name = "dept_no", length = 4, nullable = false, columnDefinition = "CHAR(4)")
	private String deptNo;

	@Column(name = "dept_name", length = 40, unique = true)
	private String deptName;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<DeptEmp> deptEmps;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<DeptManager> deptManagers;

	public Department() {
		super();
	}

	public Department(String deptNo, String deptName, Set<DeptEmp> deptEmps) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.deptEmps = deptEmps;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<DeptEmp> getDeptEmps() {
		return deptEmps;
	}

	public void setDeptEmps(Set<DeptEmp> deptEmps) {
		this.deptEmps = deptEmps;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deptEmps, deptName, deptNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(deptEmps, other.deptEmps) && Objects.equals(deptName, other.deptName)
				&& Objects.equals(deptNo, other.deptNo);
	}

	@Override
	public String toString() {
		return "Department [deptNo=" + deptNo + ", deptName=" + deptName + ", deptEmps=" + deptEmps + "]";
	}
	
	 // Convert Department entity to DepartmentDTO
    public DepartmentDTO convertToDTO() {
        return new DepartmentDTO(
            this.getDeptNo(),
            this.getDeptName()
        );
    }

}
