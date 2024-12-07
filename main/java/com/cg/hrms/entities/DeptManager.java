package com.cg.hrms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.cg.hrms.dtos.DeptManagerDTO;
import com.cg.hrms.utility.DeptManagerCompositeKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "dept_manager")
public class DeptManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DeptManagerCompositeKey id;

	@Column(name = "from_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fromDate;

	@Column(name = "to_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date toDate;

	@ManyToOne
	@MapsId("empNo")
	@JoinColumn(name = "emp_no", insertable = false, updatable = false)
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "dept_no", insertable = false, updatable = false)
	private Department department;

	public DeptManager(DeptManagerCompositeKey id, Date fromDate, Date toDate, Employee employee,
			Department department) {
		super();
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.employee = employee;
		this.department = department;
	}

	public DeptManager() {
		super();
	}

	public DeptManagerCompositeKey getId() {
		return id;
	}

	public void setId(DeptManagerCompositeKey id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "DeptManager [id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + ", employee=" + employee
				+ ", department=" + department + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(department, employee, fromDate, id, toDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeptManager other = (DeptManager) obj;
		return Objects.equals(department, other.department) && Objects.equals(employee, other.employee)
				&& Objects.equals(fromDate, other.fromDate) && Objects.equals(id, other.id)
				&& Objects.equals(toDate, other.toDate);
	}
	
    public  DeptManagerDTO fromEntity() {
        DeptManagerDTO dto = new DeptManagerDTO();
        dto.setEmpNo(this.getId().getEmpNo());
        dto.setDeptNo(this.getId().getDeptNo());
        dto.setFromDate(this.getFromDate());
        dto.setToDate(this.getToDate());
        
        return dto;
    }

}
